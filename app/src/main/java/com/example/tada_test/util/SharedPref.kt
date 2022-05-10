package com.example.tada_test.util

import android.content.Context
import android.content.SharedPreferences

class SharedPref(context: Context) {
    private val SHARED_PREF = "TadaSharedPreferences"

    var sharedPref: SharedPreferences? = context.getSharedPreferences(SHARED_PREF, 0)
    var editor: SharedPreferences.Editor? = sharedPref?.edit()

    fun setLogin(isLogin: Boolean) {
        editor?.putBoolean("isLogin", isLogin)
        editor?.commit()
    }

    fun isLogin(): Boolean? {
        return sharedPref?.getBoolean("isLogin", false)
    }

    fun setUsername(username: String) {
        editor?.putString("username", username)
        editor?.commit()
    }


    fun getUsername(): String {
        return sharedPref?.getString("username", "")?:""
    }

    fun logout() {
        editor?.clear()
        editor?.commit()
    }
}