package com.example.tada_test.datastore

import com.example.tada_test.DBHelper
import com.example.tada_test.model.UserLogin
import com.google.common.truth.Truth
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class LoginAndRegisterLocalDataStoreImplTest {
    @Mock
    val dbHelper: DBHelper? = null

    @Before
    fun init() {
        MockitoAnnotations.openMocks(this)
    }

    @Test
    fun getLoginData() {
        runBlocking {
            Mockito.`when`(dbHelper?.getUser("bayu", "123")).thenReturn(arrayListOf(UserLogin("1","bayu", "123")))
            val localAddData = LoginAndRegisterLocalDataStoreImpl(dbHelper!!)
            val res = localAddData.getLoginData("bayu", "123")
            Truth.assertThat(res).isEqualTo(UserLogin("1","bayu", "123"))
        }
    }

    @Test
    fun getLoginDataError() {
        runBlocking {
            Mockito.`when`(dbHelper?.getUser("bayu", "123")).thenThrow(
                ArrayIndexOutOfBoundsException()
            )
            val localAddData = LoginAndRegisterLocalDataStoreImpl(dbHelper!!)
            try {
                localAddData.getLoginData("bayu", "123")
                throw RuntimeException("Is must not be here")
            } catch (e: Exception) { }
        }
    }

    @Test
    fun addUserData() {
        runBlocking {
            Mockito.`when`(dbHelper?.addUser("bayu", "123")).thenReturn("success")
            val localAddData = LoginAndRegisterLocalDataStoreImpl(dbHelper!!)
            val res = localAddData.addUserData("bayu", "123")
            Truth.assertThat(res).isEqualTo("success")
        }
    }

    @Test
    fun addUserDataError() {
        runBlocking {
            Mockito.`when`(dbHelper?.addUser("bayu", "123")).thenThrow(
                ArrayIndexOutOfBoundsException()
            )
            val localAddData = LoginAndRegisterLocalDataStoreImpl(dbHelper!!)
            try {
                localAddData.addUserData("bayu", "123")
                throw RuntimeException("Is must not be here")
            } catch (e: Exception) { }
        }
    }
}