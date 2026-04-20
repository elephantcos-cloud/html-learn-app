package com.htmllearn.app.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "badge")
data class BadgeEntity(
    @PrimaryKey val id: String,
    val earnedAt: String = ""
)
