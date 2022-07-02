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

    private lateinit var scaleType: ScaleType

    override val layoutResourceId: Int = R.layout.fragment_splash
    override val viewModel: SplashContract.ViewModel by viewModels<SplashViewModel>()

    override fun onInitialize() {
        binding.lifecycleOwner = this

        viewModel.fetchScaleType()
        viewModel.checkScaleType.observe(viewLifecycleOwner) { event ->
            event.getAvailableEvent()?.let {
                scaleType = it.data ?: ScaleType.METRIC
            }
        }
        viewModel.checkLoggedIn()
        viewModel.hasSignedInObservable.observe(viewLifecycleOwner) { event ->
            event.getAvailableEvent()?.let {
                loadUserState(it)
            }
        }


    }

    override fun loadUserState(result: Result<Boolean?>) {
        when(result) {
            is Loading -> {}
            is Error -> showErrorMessage(result, binding.root)
            is Success -> {
                if (result.data == true) {
                    openHomePage()
                } else {
                    openOnBoardingPage()
                }
            }
        }
    }

    override fun openOnBoardingPage() {
        val directions = SplashFragmentDirections.actionSplashFragmentToOnBoardingFragment()
        findNavController().navigate(directions)
    }

    override fun openHomePage() {
        val directions = SplashFragmentDirections.actionSplashFragmentToHomeFragment()
        findNavController().navigate(directions)
    }

}