package com.htmllearn.app.data.model

data class Chapter(
    val id: String,
    val title: String,
    val description: String,
    val color: Long,
    val lessons: List<Lesson>
)

data class Lesson(
    val id: String,
    val chapterId: String,
    val title: String,
    val xp: Int,
    val duration: String,
    val explanation: String,
    val codeExample: String,
    val outputHtml: String,
    val quizzes: List<Quiz>
)

data class Quiz(
    val question: String,
    val options: List<String>,
    val correctIndex: Int,
    val explanation: String
)

data class BadgeInfo(
    val id: String,
    val title: String,
    val description: String,
    val iconRes: String,
    val xpRequired: Int = 0,
    val lessonsRequired: Int = 0,
    val streakRequired: Int = 0
)

data class Project(
    val id: String,
    val title: String,
    val description: String,
    val difficulty: String,
    val tags: List<String>,
    val starterCode: String,
    val hints: List<String>
)

data class HtmlTag(
    val tag: String,
    val description: String,
    val example: String,
    val category: String
)

data class UserStats(
    val totalXp: Int,
    val level: Int,
    val streak: Int,
    val lessonsCompleted: Int,
    val quizAccuracy: Int,
    val studyGoalMinutes: Int,
    val todayStudyMinutes: Int,
    val name: String
)
