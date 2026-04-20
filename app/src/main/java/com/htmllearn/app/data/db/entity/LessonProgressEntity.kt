package com.htmllearn.app.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "lesson_progress")
data class LessonProgressEntity(
    @PrimaryKey val lessonId: String,
    val chapterId: String,
    val completed: Boolean = false,
    val completedAt: String = "",
    val xpEarned: Int = 0
)
