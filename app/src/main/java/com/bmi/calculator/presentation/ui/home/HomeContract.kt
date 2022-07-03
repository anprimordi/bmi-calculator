package com.bmi.calculator.presentation.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.bmi.calculator.domain.model.Bmi
import com.bmi.calculator.domain.model.WeightCategory
import com.bmi.calculator.domain.model.common.Result
import com.bmi.calculator.presentation.util.base.BasePresenter
import com.bmi.calculator.presentation.util.base.BaseView

interface HomeContract {
    interface View : BaseView {
        fun triggerCalculateBmi()
        fun triggerReturnNthSmallestNumber()
        fun showBmiDialog(bmi: Bmi, weightCategory: WeightCategory)
        fun showNthSmallestResultDialog(result: String)
        fun showBmiIntroDialog()
        fun showNthSmallestResultCodeDialog()
    }

    interface ViewModel : BasePresenter {
        val scaleListObservable: MutableLiveData<List<String>>
        val scaleObservable: MutableLiveData<String>
        val weightObservable: MutableLiveData<String>
        val heightObservable: MutableLiveData<String>
        val bmiObservable: LiveData<Pair<Result<Bmi>, Result<WeightCategory>>>
        val nthSmallestResultObservable: LiveData<Result<String>>
        val nthObservable: MutableLiveData<String>
        val eventCalculateNthSmallest: LiveData<Double>

        fun loadData()
        fun triggerCalculateBmi()
        fun triggerReturnNthSmallestResult()
    }
}