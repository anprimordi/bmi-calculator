package com.bmi.calculator.presentation.util.base

import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.bmi.calculator.R
import com.bmi.calculator.domain.model.common.*
import com.bmi.calculator.presentation.util.extensions.showIndefiniteSnackbar
import com.bmi.calculator.presentation.util.extensions.showLongSnackbar
import com.bmi.calculator.presentation.util.extensions.showMessageDialog
import com.bmi.calculator.presentation.util.extensions.showSnackbar

open class BaseActivity : AppCompatActivity(), BaseView {

    override fun <T> getErrorMessage(result: Error<T>): String {
        return when (result) {
            is AuthError -> getString(R.string.msg_error_general_auth)
            is NetworkError -> getString(R.string.msg_error_general_network)
            is NotFoundError -> getString(R.string.msg_error_general_not_found)
            is InvalidDataError -> getString(R.string.msg_error_invalid_data)
            is GeneralError -> result.message
            is UnsupportedError -> result.message
        }
    }

    override fun <T> showErrorMessage(result: Error<T>, view: View) {
        val errorMessage = getErrorMessage(result)
        when (result) {
            is AuthError -> showSnackbar(view, errorMessage)
            is NetworkError -> showIndefiniteSnackbar(view, errorMessage)
            is NotFoundError -> showSnackbar(view, errorMessage)
            is UnknownError -> showMessageDialog(errorMessage)
            is UnsupportedError -> showMessageDialog(errorMessage)
            is GeneralError -> showLongSnackbar(view, errorMessage)
            is InvalidDataError -> showSnackbar(view, errorMessage)
        }
    }

    override fun showUnderDevelopmentMessage() {showMessageDialog(R.string.msg_under_development)
    }

    override fun closePage() {
        finish()
    }

}