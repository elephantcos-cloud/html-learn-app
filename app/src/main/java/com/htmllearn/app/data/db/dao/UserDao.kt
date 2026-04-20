package com.htmllearn.app.data.db.dao

import androidx.room.*
import com.htmllearn.app.data.db.entity.UserEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Query("SELECT * FROM user WHERE id=1")
    fun getUser(): Flow<UserEntity?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: UserEntity)

    @Update
    suspend fun updateUser(user: UserEntity)

    @Query("UPDATE user SET totalXp=totalXp+:xp, level=:level WHERE id=1")
    suspend fun addXp(xp: Int, level: Int)

    @Query("UPDATE user SET streak=:streak, lastStudyDate=:date WHERE id=1")
    suspend fun updateStreak(streak: Int, date: String)

    @Query("UPDATE user SET todayStudyMinutes=:mins WHERE id=1")
    suspend fun updateTodayStudy(mins: Int)

    @Query("UPDATE user SET totalLessonsCompleted=totalLessonsCompleted+1 WHERE id=1")
    suspend fun incrementLessons()
}
