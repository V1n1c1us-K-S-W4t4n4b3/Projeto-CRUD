package com.kzdev.projetocrud.repository

import androidx.lifecycle.LiveData
import com.kzdev.projetocrud.data.db.entity.SubscriberEntity

interface SubscriberRepository {

    suspend fun insertSubscriber(name: String, email: String): Long
    suspend fun updateSubscriber(id: Long, name: String, email: String)
    suspend fun deleteSubscriber(id: Long)
    suspend fun deleteAllSubscrubers()
    suspend fun getAllSubscribers(): List<SubscriberEntity>

}