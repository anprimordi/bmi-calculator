package com.bmi.calculator.presentation.ui.splash

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.bmi.calculator.R
import com.bmi.calculator.databinding.FragmentSplashBinding
import com.bmi.calculator.domain.model.ScaleType
import com.bmi.calculator.domain.model.common.Error
import com.bmi.calculator.domain.model.common.Loading
import com.bmi.calculator.domain.model.common.Result
import com.bmi.calculator.domain.model.common.Success
import com.bmi.calculator.presentation.util.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashFragment : BaseFragment<FragmentSplashBinding, SplashContract.ViewModel>(), SplashContract.View {

    override val layoutResourceId: Int = R.layout.fragment_splash
    override val viewModel: SplashContract.ViewModel by viewModels<SplashViewModel>()

    override fun onInitialize() {
        viewModel.loadData()
        openHomePage()
    }

    override fun openHomePage() {
        val directions = SplashFragmentDirections.actionSplashFragmentToHomeFragment()
        findNavController().navigate(directions)
    }

}