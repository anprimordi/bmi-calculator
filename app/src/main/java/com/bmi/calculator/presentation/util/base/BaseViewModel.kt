package com.bmi.calculator.presentation.util.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bmi.calculator.presentation.util.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
open class BaseViewModel @Inject constructor(
//    @Repository private val userDataSource: UserDataSource
) : ViewModel(), BasePresenter {

    private val _stateLoading = MediatorLiveData<Boolean>()
    override val stateLoading: LiveData<Boolean>
        get() = _stateLoading

    private val _eventMessage = MutableLiveData<Event<String>>()
    override val eventMessage: LiveData<Event<String>>
        get() = _eventMessage

    private val _eventImportantMessage = MutableLiveData<Event<String>>()
    override val eventImportantMessage: LiveData<Event<String>>
        get() = _eventImportantMessage

    init {
        _stateLoading.value = false
    }

    override fun isLoading(): Boolean = stateLoading.value == true

    override fun setLoading(isLoading: Boolean) {
        _stateLoading.postValue(isLoading)
    }

    override fun postMessageEvent(message: String) {
        _eventMessage.postValue(Event(content = message))
    }

    override fun postImportantMessageEvent(message: String) {
        _eventImportantMessage.postValue(Event(content = message))
    }

//    override suspend fun getCurrentUser(): Guest? {
//        val result = accountDataSource.getAccount()
//        return if (result is Success) result.data else null
//        return null
//    }

}