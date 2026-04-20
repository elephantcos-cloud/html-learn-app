package com.htmllearn.app.data.db.dao

import androidx.room.*
import com.htmllearn.app.data.db.entity.QuizResultEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface QuizResultDao {
    @Query("SELECT * FROM quiz_result ORDER BY id DESC")
    fun getAllResults(): Flow<List<QuizResultEntity>>

    @Query("SELECT * FROM quiz_result WHERE lessonId=:lessonId ORDER BY id DESC LIMIT 1")
    suspend fun getLatest(lessonId: String): QuizResultEntity?

    @Insert
    suspend fun insert(r: QuizResultEntity)
}
