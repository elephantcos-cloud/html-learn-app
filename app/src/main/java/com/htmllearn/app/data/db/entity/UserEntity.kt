package com.htmllearn.app.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class UserEntity(
    @PrimaryKey val id: Int = 1,
    val name: String = "শিক্ষার্থী",
    val totalXp: Int = 0,
    val level: Int = 1,
    val streak: Int = 0,
    val lastStudyDate: String = "",
    val studyGoalMinutes: Int = 30,
    val totalLessonsCompleted: Int = 0,
    val totalQuizCorrect: Int = 0,
    val totalQuizAttempts: Int = 0,
    val joinDate: String = "",
    val notificationsEnabled: Boolean = true,
    val reminderHour: Int = 20,
    val reminderMinute: Int = 0,
    val todayStudyMinutes: Int = 0
)
