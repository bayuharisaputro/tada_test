package com.example.tada_test.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.tada_test.model.UserLogin

abstract class LoginAndRegisterViewModel : ViewModel() {

    abstract val ldIsLoading: LiveData<Boolean>

    abstract val ldErrorHandler: LiveData<Exception?>

    abstract val ldDataLogin: LiveData<UserLogin?>

    abstract val ldAddUserData: LiveData<String?>

    abstract fun requestGetDataUser(username: String, password: String)

    abstract fun requestAddDataUser(username: String, password: String)

}