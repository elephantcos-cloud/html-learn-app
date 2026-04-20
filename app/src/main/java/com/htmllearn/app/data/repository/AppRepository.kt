package com.htmllearn.app.data.repository

import android.content.Context
import com.htmllearn.app.data.content.*
import com.htmllearn.app.data.db.AppDatabase
import com.htmllearn.app.data.db.entity.*
import com.htmllearn.app.data.model.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class AppRepository(context: Context) {

    private val db     = AppDatabase.get(context)
    private val userDao  = db.userDao()
    private val progressDao = db.lessonProgressDao()
    private val quizDao  = db.quizResultDao()
    private val badgeDao = db.badgeDao()

    companion object {
        @Volatile private var INSTANCE: AppRepository? = null
        fun get(context: Context) = INSTANCE ?: synchronized(this) {
            AppRepository(context.applicationContext).also { INSTANCE = it }
        }
    }

    // ── User ──────────────────────────────────────────────────────────
    fun userFlow(): Flow<UserEntity?> = userDao.getUser()

    suspend fun ensureUser() {
        val u = userDao.getUser().first()
        if (u == null) {
            userDao.insertUser(UserEntity(
                joinDate = today()
            ))
        }
    }

    suspend fun updateSettings(user: UserEntity) = userDao.updateUser(user)

    suspend fun updateStudyGoal(hours: Int, minute: Int, enabled: Boolean) {
        val u = userDao.getUser().first() ?: return
        userDao.updateUser(u.copy(
            reminderHour = hours,
            reminderMinute = minute,
            notificationsEnabled = enabled
        ))
    }

    // ── Content ───────────────────────────────────────────────────────
    fun getChapters(): List<Chapter> = allChapters
    fun getProjects(): List<Project> = allProjects
    fun getTags(): List<HtmlTag> = allTags
    fun getBadgeDefs(): List<BadgeInfo> = allBadges

    fun getChapter(id: String) = allChapters.find { it.id == id }
    fun getLesson(lessonId: String) = allChapters.flatMap { it.lessons }.find { it.id == lessonId }

    // ── Progress ──────────────────────────────────────────────────────
    fun progressFlow(): Flow<List<LessonProgressEntity>> = progressDao.getAllProgress()

    suspend fun completeLesson(lesson: Lesson) {
        val existing = progressDao.getProgress(lesson.id)
        if (existing?.completed == true) return

        progressDao.insertProgress(LessonProgressEntity(
            lessonId = lesson.id,
            chapterId = lesson.chapterId,
            completed = true,
            completedAt = today(),
            xpEarned = lesson.xp
        ))
        userDao.incrementLessons()

        // XP & Level update
        val u = userDao.getUser().first() ?: return
        val newXp = u.totalXp + lesson.xp
        val newLevel = xpToLevel(newXp)
        userDao.addXp(lesson.xp, newLevel)

        // Streak update
        updateStreak()

        // Check badges
        checkBadges(newXp, newLevel)
    }

    suspend fun saveQuizResult(lessonId: String, score: Int, total: Int) {
        quizDao.insert(QuizResultEntity(lessonId = lessonId, score = score, total = total, completedAt = today()))
    }

    // ── Badges ────────────────────────────────────────────────────────
    fun badgesFlow(): Flow<List<BadgeEntity>> = badgeDao.getAll()

    private suspend fun checkBadges(xp: Int, level: Int) {
        val u = userDao.getUser().first() ?: return
        val lessons = u.totalLessonsCompleted

        allBadges.forEach { badge ->
            val earned = when {
                badge.xpRequired > 0 && xp >= badge.xpRequired -> true
                badge.lessonsRequired > 0 && lessons >= badge.lessonsRequired -> true
                badge.streakRequired > 0 && u.streak >= badge.streakRequired -> true
                else -> false
            }
            if (earned) badgeDao.insert(BadgeEntity(badge.id, today()))
        }
    }

    private suspend fun updateStreak() {
        val u = userDao.getUser().first() ?: return
        val todayStr = today()
        val yesterday = LocalDate.now().minusDays(1).format(DateTimeFormatter.ISO_DATE)
        val newStreak = when (u.lastStudyDate) {
            todayStr -> u.streak
            yesterday -> u.streak + 1
            "" -> 1
            else -> 1
        }
        userDao.updateStreak(newStreak, todayStr)
    }

    // ── Stats ─────────────────────────────────────────────────────────
    fun statsFlow(): Flow<UserStats> = userDao.getUser().map { u ->
        if (u == null) UserStats(0,1,0,0,0,30,0,"শিক্ষার্থী")
        else UserStats(
            totalXp = u.totalXp,
            level = u.level,
            streak = u.streak,
            lessonsCompleted = u.totalLessonsCompleted,
            quizAccuracy = if (u.totalQuizAttempts > 0) (u.totalQuizCorrect * 100 / u.totalQuizAttempts) else 0,
            studyGoalMinutes = u.studyGoalMinutes,
            todayStudyMinutes = u.todayStudyMinutes,
            name = u.name
        )
    }

    // ── Helpers ───────────────────────────────────────────────────────
    private fun today() = LocalDate.now().format(DateTimeFormatter.ISO_DATE)

    private fun xpToLevel(xp: Int): Int = when {
        xp < 100  -> 1
        xp < 250  -> 2
        xp < 500  -> 3
        xp < 900  -> 4
        xp < 1500 -> 5
        xp < 2500 -> 6
        xp < 4000 -> 7
        xp < 6000 -> 8
        xp < 9000 -> 9
        else      -> 10
    }

    fun xpForNextLevel(xp: Int): Pair<Int,Int> {
        val thresholds = listOf(0,100,250,500,900,1500,2500,4000,6000,9000,15000)
        val level = xpToLevel(xp)
        val current = thresholds.getOrElse(level - 1) { 0 }
        val next = thresholds.getOrElse(level) { 15000 }
        return Pair(xp - current, next - current)
    }
}
