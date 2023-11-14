package com.kzdev.projetocrud.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kzdev.projetocrud.R
import com.kzdev.projetocrud.repository.SubscriberRepository
import kotlinx.coroutines.launch

class CRUDViewModel(private val repository: SubscriberRepository) : ViewModel() {


    private val _subscribersStateEventData = MutableLiveData<SubscriberState>()
    val subscribersStateEventData: LiveData<SubscriberState>
        get() = _subscribersStateEventData


    private val _messageEventData = MutableLiveData<Int>()
    val messageEventData: LiveData<Int>
        get() = _messageEventData


    fun addSubscriber(name: String, email: String) = viewModelScope.launch {
        try {

            val id = repository.insertSubscriber(name, email)

            if (id > 0) {

                _subscribersStateEventData.value = SubscriberState.Inserted
                _messageEventData.value = R.string.subscribe_insert_successfully

            }
        } catch (ex: Exception) {

            _messageEventData.value = R.string.subscribe_error_to_insert

            Log.e("", ex.toString())
        }
    }

    sealed class SubscriberState {
        object Inserted : SubscriberState()
    }

    companion object {
        private val TAG = CRUDViewModel::class.java.simpleName

    }
}