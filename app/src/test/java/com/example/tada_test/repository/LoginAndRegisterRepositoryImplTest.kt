package com.example.tada_test.repository


import com.example.tada_test.datastore.LoginAndRegisterLocalDataStoreImpl
import com.example.tada_test.model.UserLogin
import com.google.common.truth.Truth
import kotlinx.coroutines.runBlocking
import org.junit.Before

import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class LoginAndRegisterRepositoryImplTest {

    @Mock
    var localDataStoreImpl: LoginAndRegisterLocalDataStoreImpl? = null

    @Before
    fun init() {
        MockitoAnnotations.openMocks(this)
    }

    @Test
    fun getLoginData() {
        runBlocking {
            Mockito.`when`(localDataStoreImpl?.getLoginData("bayu", "123"))
                .thenReturn(UserLogin("1", "bayu", "123"))
            val repos: LoginAndRegisterRepositoryImpl? =
                LoginAndRegisterRepositoryImpl(localDataStoreImpl!!)

            val res = repos?.getLoginData("bayu", "123")
            Truth.assertThat(res).isNotNull()
            Truth.assertThat(res).isEqualTo(UserLogin("1", "bayu", "123"))
        }
    }

    @Test
    fun getLoginDataError() {
        runBlocking {
            Mockito.`when`(localDataStoreImpl?.getLoginData("bayu", "123"))
                .thenThrow(
                    ArrayIndexOutOfBoundsException()
                )

            val repos: LoginAndRegisterRepositoryImpl? =
                LoginAndRegisterRepositoryImpl(localDataStoreImpl!!)

            try {
                repos?.getLoginData("bayu", "123")
                throw RuntimeException("Is must not be here")
            } catch (e: Exception) { }

        }
    }

    @Test
    fun addUserData() {
        runBlocking {
            Mockito.`when`(localDataStoreImpl?.addUserData("bayu", "123"))
                .thenReturn("success")
            val repos: LoginAndRegisterRepositoryImpl? =
                LoginAndRegisterRepositoryImpl(localDataStoreImpl!!)

            val res = repos?.addUserData("bayu", "123")
            Truth.assertThat(res).isNotNull()
            Truth.assertThat(res).isEqualTo("success")
        }
    }

    @Test
    fun addUserDataError() {
        runBlocking {
            Mockito.`when`(localDataStoreImpl?.addUserData("bayu", "123"))
                .thenThrow(
                    ArrayIndexOutOfBoundsException()
                )

            val repos: LoginAndRegisterRepositoryImpl? =
                LoginAndRegisterRepositoryImpl(localDataStoreImpl!!)

            try {
                repos?.addUserData("bayu", "123")
                throw RuntimeException("Is must not be here")
            } catch (e: Exception) { }

        }
    }
}