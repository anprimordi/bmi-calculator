package com.bmi.calculator.data.local

import android.content.Context
import android.content.SharedPreferences
import com.bmi.calculator.BuildConfig
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppPreference @Inject constructor(
    @ApplicationContext context: Context
) {

    companion object {
        private const val PREFERENCE_NAME = BuildConfig.PREF_NAME

        //region Key
        const val KEY_USER = "key_json_string_user"
        const val KEY_SIGNED_IN = "key_string_signed_in"
        const val KEY_SCALE_TYPE = "key_string_scale_type"
        //endregion Key
    }

    private val sharedPref = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE)

    fun get(): SharedPreferences {
        return sharedPref
    }

    fun editor(): SharedPreferences.Editor {
        return sharedPref.edit()
    }

    fun clear() {
        editor().clear().apply()
    }

}