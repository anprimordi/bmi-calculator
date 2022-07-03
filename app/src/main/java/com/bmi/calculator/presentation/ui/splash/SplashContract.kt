package com.bmi.calculator.presentation.ui.splash

import androidx.lifecycle.LiveData
import com.bmi.calculator.domain.model.ScaleType
import com.bmi.calculator.domain.model.User
import com.bmi.calculator.domain.model.common.Result
import com.bmi.calculator.presentation.util.Event
import com.bmi.calculator.presentation.util.base.BasePresenter
import com.bmi.calculator.presentation.util.base.BaseView

interface SplashContract {

    interface View : BaseView {
        fun openHomePage()
    }

    interface ViewModel : BasePresenter {
        fun loadData()
    }
}