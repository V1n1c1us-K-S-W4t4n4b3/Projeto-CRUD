package com.kzdev.projetocrud.ui.subscriberlist

import androidx.lifecycle.ViewModel
import com.kzdev.projetocrud.repository.SubscriberRepository

class SubscriberListViewModel(private val repository: SubscriberRepository) : ViewModel() {


    val allSubscribersEvents = repository.getAllSubscribers()

}