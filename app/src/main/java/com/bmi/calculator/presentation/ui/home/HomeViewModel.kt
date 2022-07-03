package com.bmi.calculator.presentation.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.bmi.calculator.domain.datasource.BmiDataSource
import com.bmi.calculator.domain.datasource.UserDataSource
import com.bmi.calculator.domain.model.Bmi
import com.bmi.calculator.domain.model.ScaleType
import com.bmi.calculator.domain.model.WeightCategory
import com.bmi.calculator.domain.model.common.Error
import com.bmi.calculator.domain.model.common.GeneralError
import com.bmi.calculator.domain.model.common.Result
import com.bmi.calculator.domain.model.common.Success
import com.bmi.calculator.presentation.util.base.BaseViewModel
import com.krakatio.aplikasiconvertpulsa.di.datasource.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    @Repository private val userDataSource: UserDataSource,
    @Repository private val bmiDataSource: BmiDataSource
) : BaseViewModel(), HomeContract.ViewModel {

    override val scaleListObservable: MutableLiveData<List<String>> =
        MutableLiveData<List<String>>()
    override val scaleObservable: MutableLiveData<String> = MutableLiveData<String>()
    override val weightObservable: MutableLiveData<String> = MutableLiveData<String>()
    override val heightObservable: MutableLiveData<String> = MutableLiveData<String>()

    private val _bmiObservable = MutableLiveData<Pair<Result<Bmi>, Result<WeightCategory>>>()
    override val bmiObservable: LiveData<Pair<Result<Bmi>, Result<WeightCategory>>>
        get() = _bmiObservable
    private val _nthSmallestResultObservable = MutableLiveData<Result<String>>()
    override val nthSmallestResultObservable: LiveData<Result<String>>
        get() = _nthSmallestResultObservable
    override val nthObservable: MutableLiveData<String> = MutableLiveData<String>()
    private val _eventCalculateNthSmallest = MutableLiveData<Double>()
    override val eventCalculateNthSmallest: LiveData<Double>
        get() = _eventCalculateNthSmallest

    override fun loadData() {
        viewModelScope.launch {
            val list = ScaleType.values().map { it.name }.toList()
            scaleListObservable.postValue(list)
        }
    }

    override fun triggerCalculateBmi() {
        val scaleType = scaleObservable.value
        val weight = weightObservable.value
        val height = heightObservable.value

        viewModelScope.launch {
            if (weight != null && height != null) {
                if (scaleType == ScaleType.METRIC.name) {
                    val result = bmiDataSource.countBmi(ScaleType.METRIC, weight, height)

                    if (result.data != null) {

                        val weightCategoryResult =
                            bmiDataSource.getWeightCategory(result.data!!.bmi)

                        _bmiObservable.postValue(Pair(result, weightCategoryResult))

                    } else {
                        _bmiObservable.postValue(Pair(Error.general(), Error.general()))
                    }
                } else {
                    val result = bmiDataSource.countBmi(ScaleType.IMPERIAL, weight, height)

                    if (result.data != null) {

                        val weightCategoryResult =
                            bmiDataSource.getWeightCategory(result.data!!.bmi)

                        _bmiObservable.postValue(Pair(result, weightCategoryResult))

                    } else {
                        _bmiObservable.postValue(Pair(Error.general(), Error.general()))
                    }
                }
            } else {
                _bmiObservable.postValue(Pair(Error.general(), Error.general()))
            }
        }

    }

    override fun triggerReturnNthSmallestResult() {
        val numbers = intArrayOf(5408, 6604, 32158, 84984, 8778, 34871)
        val n = nthObservable.value?.toInt()

        Timber.e(nthObservable.value)

        viewModelScope.launch {
            if (n != 0 && n != null) {
                numbers.sort()

                try {
                    val result = numbers[n - 1].toString()
                    _nthSmallestResultObservable.postValue(Success(result))
                } catch (ex: Exception) {
                    _nthSmallestResultObservable.postValue(GeneralError("Error!\n${ex.message!!}"))
                }
            } else {
                _nthSmallestResultObservable.postValue(GeneralError("Error: n cannot be less than 1"))
            }
        }
    }
}