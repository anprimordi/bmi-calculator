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
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    @Repository private val userDataSource: UserDataSource
) : BaseViewModel(), SplashContract.ViewModel {
    override fun loadData() {
        viewModelScope.launch {
            delay(1000)
        }
    }


}