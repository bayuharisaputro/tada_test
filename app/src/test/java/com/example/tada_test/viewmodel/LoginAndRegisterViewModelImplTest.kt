package com.example.tada_test.viewmodel

import android.os.Build
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.tada_test.model.UserLogin
import com.example.tada_test.repository.LoginAndRegisterRepositoryImpl
import com.google.common.truth.Truth
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule

import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.robolectric.annotation.Config

@Config(maxSdk = Build.VERSION_CODES.P)
@RunWith(AndroidJUnit4::class)
class LoginAndRegisterViewModelImplTest {
    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    @Mock
    var repo : LoginAndRegisterRepositoryImpl? = null

    @Before
    fun init(){
        MockitoAnnotations.openMocks(this)
    }

    @Test
    fun requestGetDataUser() {
        runBlocking {
            val vm = LoginAndRegisterViewModelImpl(repo!!)
            Mockito.`when`(repo?.getLoginData("bayu", "123")).thenReturn(UserLogin("1", "bayu", "123"))

            vm.requestGetDataUser("bayu", "123")

            val dataUser = vm.ldDataLogin.value
            val error = vm.ldErrorHandler.value
            val isLoading = vm.ldIsLoading.value
            Truth.assertThat(dataUser).isNotNull()
            Truth.assertThat(error).isNull()
            Truth.assertThat(isLoading).isFalse()
        }
    }

    @Test
    fun requestGetDataUserError() {
        runBlocking {
            val vm = LoginAndRegisterViewModelImpl(repo!!)
            Mockito.`when`(repo?.getLoginData("bayu", "123")).thenThrow(
                ArrayIndexOutOfBoundsException()
            )
            vm.requestGetDataUser("bayu", "123")

            val dataUser = vm.ldDataLogin.value
            val error = vm.ldErrorHandler.value
            val isLoading = vm.ldIsLoading.value
            Truth.assertThat(dataUser).isNull()
            Truth.assertThat(error).isNotNull()
            Truth.assertThat(isLoading).isFalse()
        }
    }

    @Test
    fun requestAddDataUser() {
        runBlocking {
            val vm = LoginAndRegisterViewModelImpl(repo!!)
            Mockito.`when`(repo?.addUserData("bayu", "123")).thenReturn("success")

            vm.requestAddDataUser("bayu", "123")

            val addUser = vm.ldAddUserData.value
            val error = vm.ldErrorHandler.value
            val isLoading = vm.ldIsLoading.value
            Truth.assertThat(addUser).isNotNull()
            Truth.assertThat(error).isNull()
            Truth.assertThat(isLoading).isFalse()
        }
    }

    @Test
    fun requestAddDataUserError() {
        runBlocking {
            val vm = LoginAndRegisterViewModelImpl(repo!!)
            Mockito.`when`(repo?.addUserData("bayu", "123")).thenThrow(
                ArrayIndexOutOfBoundsException()
            )
            vm.requestAddDataUser("bayu", "123")

            val addUser = vm.ldAddUserData.value
            val error = vm.ldErrorHandler.value
            val isLoading = vm.ldIsLoading.value
            Truth.assertThat(addUser).isNull()
            Truth.assertThat(error).isNotNull()
            Truth.assertThat(isLoading).isFalse()
        }
    }
}