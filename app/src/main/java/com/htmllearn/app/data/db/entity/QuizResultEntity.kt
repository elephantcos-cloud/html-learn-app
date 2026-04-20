package com.htmllearn.app.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "quiz_result")
data class QuizResultEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val lessonId: String,
    val score: Int,
    val total: Int,
    val completedAt: String = ""
)
