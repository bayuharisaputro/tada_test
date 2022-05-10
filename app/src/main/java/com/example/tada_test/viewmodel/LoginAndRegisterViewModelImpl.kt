package com.example.tada_test.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.tada_test.model.UserLogin
import com.example.tada_test.repository.LoginAndRegisterRepository
import kotlinx.coroutines.launch
import javax.inject.Inject


class LoginAndRegisterViewModelImpl @Inject constructor(
        private val repository: LoginAndRegisterRepository
) : LoginAndRegisterViewModel() {

    private val mIsLoading: MutableLiveData<Boolean> = MutableLiveData(false)
    private val mErrorHandler: MutableLiveData<Exception?> = MutableLiveData()
    private val mDataLogin: MutableLiveData<UserLogin?> = MutableLiveData()
    private val mAddUserData: MutableLiveData<String?> = MutableLiveData()

    override val ldIsLoading: LiveData<Boolean> = mIsLoading
    override val ldErrorHandler: LiveData<Exception?> = mErrorHandler
    override val ldDataLogin: LiveData<UserLogin?> = mDataLogin
    override val ldAddUserData: LiveData<String?> = mAddUserData


    override fun requestGetDataUser(username: String, password: String) {
        viewModelScope.launch {
            mIsLoading.value = true
            try {
                mDataLogin.value = repository.getLoginData(username, password)
                mErrorHandler.value = null
            }
            catch (e: Exception) {
                mDataLogin.value = null
                mErrorHandler.value = e
            }
            finally {
                mIsLoading.value = false
            }
        }
    }

    override fun requestAddDataUser(username: String, password: String) {
        viewModelScope.launch {
            mIsLoading.value = true
            try {
                mAddUserData.value = repository.addUserData(username, password)
                mErrorHandler.value = null
            } catch (e: Exception) {
                mAddUserData.value = null
                mErrorHandler.value = e
            } finally {
                mIsLoading.value = false
            }
        }
    }
}