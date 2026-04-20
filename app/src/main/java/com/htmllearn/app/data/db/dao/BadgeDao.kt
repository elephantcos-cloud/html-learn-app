package com.htmllearn.app.data.db.dao

import androidx.room.*
import com.htmllearn.app.data.db.entity.BadgeEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface BadgeDao {
    @Query("SELECT * FROM badge")
    fun getAll(): Flow<List<BadgeEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(b: BadgeEntity)
}
