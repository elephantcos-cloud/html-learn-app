package com.htmllearn.app.data.db.dao

import androidx.room.*
import com.htmllearn.app.data.db.entity.LessonProgressEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface LessonProgressDao {
    @Query("SELECT * FROM lesson_progress")
    fun getAllProgress(): Flow<List<LessonProgressEntity>>

    @Query("SELECT * FROM lesson_progress WHERE lessonId=:lessonId")
    suspend fun getProgress(lessonId: String): LessonProgressEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProgress(p: LessonProgressEntity)

    @Query("SELECT COUNT(*) FROM lesson_progress WHERE completed=1 AND chapterId=:chapterId")
    suspend fun completedCountInChapter(chapterId: String): Int
}
