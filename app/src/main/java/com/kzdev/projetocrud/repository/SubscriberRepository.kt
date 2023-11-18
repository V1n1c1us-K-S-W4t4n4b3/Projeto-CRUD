package com.kzdev.projetocrud.repository

import com.kzdev.projetocrud.data.db.entity.SubscriberEntity


interface SubscriberRepository {

    suspend fun deleteSubscriber(id: Long)
    suspend fun getAllSubscriber(): List<SubscriberEntity>
    suspend fun deleteAllSubscriber()
    suspend fun updateSubscriber(id: Long, name: String, birth: String, cpf: String, tel: String)
    suspend fun insertSubscriber(name: String, birth: String, cpf: String, tel: String): Long
}