package com.bmi.calculator.presentation.ui.home

import android.widget.ArrayAdapter
import androidx.fragment.app.viewModels
import com.bmi.calculator.R
import com.bmi.calculator.databinding.FragmentHomeBinding
import com.bmi.calculator.domain.model.Bmi
import com.bmi.calculator.domain.model.ScaleType
import com.bmi.calculator.domain.model.WeightCategory
import com.bmi.calculator.domain.model.common.Error
import com.bmi.calculator.domain.model.common.Loading
import com.bmi.calculator.domain.model.common.Success
import com.bmi.calculator.presentation.util.base.BaseFragment
import com.bmi.calculator.presentation.util.extensions.showMessageDialog
import com.bmi.calculator.presentation.util.extensions.showSnackbar
import com.bmi.calculator.presentation.util.extensions.showTitleWithMessageDialog
import com.google.android.material.textfield.MaterialAutoCompleteTextView
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding, HomeContract.ViewModel>(),
    HomeContract.View {

    override val layoutResourceId: Int = R.layout.fragment_home
    override val viewModel: HomeContract.ViewModel by viewModels<HomeViewModel>()

    override fun onInitialize() {
        binding.view = this
        binding.viewModel = viewModel

        viewModel.loadData()

        val list = ScaleType.values().map { it.name }
        binding.editUnit.setDropdownMenu(list)

        viewModel.scaleObservable.observe(viewLifecycleOwner) {
            when (it) {
                ScaleType.METRIC.name -> {
                    binding.fieldWeight.suffixText = "kg"
                    binding.fieldHeight.suffixText = "m"
                }
                ScaleType.IMPERIAL.name -> {
                    binding.fieldWeight.suffixText = "lb"
                    binding.fieldHeight.suffixText = "in"
                }
                else -> {
                    binding.fieldWeight.suffixText = ""
                    binding.fieldHeight.suffixText = ""
                }
            }
        }

        viewModel.bmiObservable.observe(viewLifecycleOwner) {
            when (it.first) {
                is Loading -> {}
                is Error -> {
                    showSnackbar(binding.root, it.first.message)
                }
                is Success -> {
                    showBmiDialog(it.first.data!!, it.second.data!!)
                }
            }
        }

        viewModel.nthSmallestResultObservable.observe(viewLifecycleOwner) {
            when (it) {
                is Loading -> {}
                is Error -> {
                    Timber.e(it.message)
                    showSnackbar(binding.root, it.message)
                }
                is Success -> {
                    showNthSmallestResultDialog(it.data)
                }
            }
        }
    }

    override fun triggerCalculateBmi() {
        viewModel.triggerCalculateBmi()
    }

    override fun triggerReturnNthSmallestNumber() {
        viewModel.triggerReturnNthSmallestResult()
    }

    override fun showBmiDialog(bmi: Bmi, weightCategory: WeightCategory) {

        val bmiCount = bmi.bmi.toString()
        val weight = bmi.weight.toString()
        val height = bmi.height.toString()
        val bodyType =
            if (bmi.bodyType.isNotEmpty() && bmi.bodyType.isNotBlank()) bmi.bodyType else weightCategory.weightCategory

        showTitleWithMessageDialog(
            title = "Body Mass Index",
            message = "BMI: $bmiCount \nBody Type: $bodyType \nWeight: $weight \nHeight: $height "
        )
    }

    override fun showNthSmallestResultDialog(result: String) {
        showMessageDialog(result)
    }

    override fun showBmiIntroDialog() {
        val title = getString(R.string.title_bmi_intro)
        val message = getString(R.string.plain_bmi_intro)

        showTitleWithMessageDialog(title, message)
    }

    override fun showNthSmallestResultCodeDialog() {
        val title = getString(R.string.title_return_nth_code)
        val message = getString(R.string.plain_return_nth_code)
        showTitleWithMessageDialog(title, message)
    }

    private fun MaterialAutoCompleteTextView.setDropdownMenu(list: List<String>) {
        val adapter = ArrayAdapter(requireContext(), R.layout.item_list, list)
        this.setAdapter(adapter)
        this.threshold = 1000
    }
}