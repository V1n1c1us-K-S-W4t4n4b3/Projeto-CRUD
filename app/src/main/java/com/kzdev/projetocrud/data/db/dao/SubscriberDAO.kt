package com.kzdev.projetocrud.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.kzdev.projetocrud.data.db.entity.SubscriberEntity


@Dao
interface SubscriberDAO {
    @Insert
    suspend fun insert(subscriber: SubscriberEntity): Long

    @Update
    suspend fun update(subscriber: SubscriberEntity)

    @Query("DELETE FROM user WHERE id = :id")
    suspend fun delete(id: Long)

    @Query("DELETE FROM user")
    suspend fun deleteAll()

    @Query("SELECT * FROM user")
    suspend fun getAll(): List<SubscriberEntity>

}