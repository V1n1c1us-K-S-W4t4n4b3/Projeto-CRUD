package com.kzdev.projetocrud.ui.subscriber

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kzdev.projetocrud.R
import com.kzdev.projetocrud.repository.SubscriberRepository
import kotlinx.coroutines.launch

class SubscriberViewModel(
    private val repository: SubscriberRepository,
) : ViewModel() {

    private val _subscriberStateEvenData = MutableLiveData<SubscriberState>()
    val subscriberStateEvenData: LiveData<SubscriberState>
        get() = _subscriberStateEvenData

    private val _messageEvenData = MutableLiveData<Int>()
    val messageEvenData: LiveData<Int>
        get() = _messageEvenData

    fun addOrUpdateSubscriber(name: String, birth: String, cpf: String, tel: String, id: Long = 0) {
        if (id > 0) {
            updateSubscriber(id, name, birth, cpf, tel)
        } else {
            insertSubscriber(name, birth, cpf, tel)
        }
    }

    private fun updateSubscriber(id: Long, name: String, birth: String, cpf: String, tel: String) =
        viewModelScope.launch {
            try {
                repository.updateSubscriber(id, name, birth, cpf, tel)

                _subscriberStateEvenData.value = SubscriberState.Update
                _messageEvenData.value = R.string.subscriber_update_successfully
            } catch (ex: Exception) {
                _messageEvenData.value = R.string.subscriber_error_to_update
                Log.e(TAG, ex.toString())
            }
        }

    private fun insertSubscriber(name: String, birth: String, cpf: String, tel: String) =
        viewModelScope.launch {
            try {
                val id = repository.insertSubscriber(name, birth, cpf, tel)
                if (id > 0) {
                    _subscriberStateEvenData.value = SubscriberState.Inserted
                    _messageEvenData.value = R.string.subscriber_insert_successfully
                }
            } catch (ex: Exception) {

                Log.e("", ex.toString())
            }

        }

    fun removeSubscriber(id: Long) = viewModelScope.launch {
        try {
            if (id > 0) {
                repository.deleteSubscriber(id)
                _subscriberStateEvenData.value = SubscriberState.Deleted
                _messageEvenData.value = R.string.subscriber_deleted_successfully
            }
        } catch (ex: Exception) {
            _messageEvenData.value = R.string.subscriber_error_to_delete
            Log.e(TAG, ex.toString())
        }
    }

    sealed class SubscriberState {
        data object Inserted : SubscriberState()
        data object Update : SubscriberState()
        data object Deleted : SubscriberState()
    }

    companion object {
        private val TAG = SubscriberViewModel::class.java.simpleName
    }
}