package com.bmi.calculator.presentation.ui.splash

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.bmi.calculator.domain.datasource.UserDataSource
import com.bmi.calculator.domain.model.ScaleType
import com.bmi.calculator.domain.model.common.Result
import com.bmi.calculator.presentation.util.Event
import com.bmi.calculator.presentation.util.base.BaseViewModel
import com.krakatio.aplikasiconvertpulsa.di.datasource.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    @Repository private val userDataSource: UserDataSource
) : BaseViewModel(), SplashContract.ViewModel {

    private val _hasSignedInObservable = MutableLiveData<Event<Result<Boolean?>>>()
    override val hasSignedInObservable: LiveData<Event<Result<Boolean?>>>
        get() = _hasSignedInObservable

    private val _checkScaleType = MutableLiveData<Event<Result<ScaleType>>>()
    override val checkScaleType: LiveData<Event<Result<ScaleType>>>
        get() = _checkScaleType

    override fun checkLoggedIn() {
        viewModelScope.launch {
            val result = userDataSource.checkHasSignedIn()
            _hasSignedInObservable.postValue(Event(result))
        }
    }

    override fun fetchScaleType() {
        viewModelScope.launch {
            val result = userDataSource.checkScaleType()
            _checkScaleType.postValue(Event(result))

            if (result.data != null) {
                userDataSource.saveScaleType(result.data ?: ScaleType.METRIC)
            }
        }
    }

}