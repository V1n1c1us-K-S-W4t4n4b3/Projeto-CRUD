package com.kzdev.projetocrud.ui.subscriberlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kzdev.projetocrud.data.db.entity.SubscriberEntity
import com.kzdev.projetocrud.repository.SubscriberRepository
import kotlinx.coroutines.launch

class SubscriberListViewModel(private val repository: SubscriberRepository) : ViewModel() {


    private val _allSubscribersEvent = MutableLiveData<List<SubscriberEntity>>()

    val allSubscribersEvents: LiveData<List<SubscriberEntity>>
        get() = _allSubscribersEvent


    fun getSubscribers() = viewModelScope.launch {
        _allSubscribersEvent.postValue(repository.getAllSubscribers())
    }

}