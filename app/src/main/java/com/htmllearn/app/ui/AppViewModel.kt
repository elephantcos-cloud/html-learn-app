package com.htmllearn.app.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.htmllearn.app.data.db.entity.BadgeEntity
import com.htmllearn.app.data.db.entity.LessonProgressEntity
import com.htmllearn.app.data.model.*
import com.htmllearn.app.data.repository.AppRepository
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class AppViewModel(app: Application) : AndroidViewModel(app) {
    private val repo = AppRepository.get(app)

    val stats   : StateFlow<UserStats> = repo.statsFlow()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), UserStats(0,1,0,0,0,30,0,"শিক্ষার্থী"))

    val progress: StateFlow<List<LessonProgressEntity>> = repo.progressFlow()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val badges  : StateFlow<List<BadgeEntity>> = repo.badgesFlow()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val chapters: List<Chapter> = repo.getChapters()
    val projects: List<Project> = repo.getProjects()
    val tags    : List<HtmlTag> = repo.getTags()
    val badgeDefs: List<BadgeInfo> = repo.getBadgeDefs()

    fun completeLesson(lesson: Lesson) = viewModelScope.launch {
        repo.completeLesson(lesson)
    }

    fun saveQuizResult(lessonId: String, score: Int, total: Int) = viewModelScope.launch {
        repo.saveQuizResult(lessonId, score, total)
    }

    fun getLesson(id: String) = repo.getLesson(id)

    fun xpProgress(xp: Int) = repo.xpForNextLevel(xp)
}
