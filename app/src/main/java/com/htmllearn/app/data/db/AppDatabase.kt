package com.htmllearn.app.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.htmllearn.app.data.db.dao.*
import com.htmllearn.app.data.db.entity.*

@Database(
    entities = [
        UserEntity::class,
        LessonProgressEntity::class,
        QuizResultEntity::class,
        BadgeEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun lessonProgressDao(): LessonProgressDao
    abstract fun quizResultDao(): QuizResultDao
    abstract fun badgeDao(): BadgeDao

    companion object {
        @Volatile private var INSTANCE: AppDatabase? = null

        fun get(context: Context): AppDatabase = INSTANCE ?: synchronized(this) {
            Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, "htmllearn.db")
                .build().also { INSTANCE = it }
        }
    }
}
