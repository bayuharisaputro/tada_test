package com.example.tada_test.repository

import com.example.tada_test.datastore.LoginAndRegisterLocalDataStore
import com.example.tada_test.model.UserLogin
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.jvm.Throws

class LoginAndRegisterRepositoryImpl @Inject constructor(
    private val localDataStore: LoginAndRegisterLocalDataStore
) : LoginAndRegisterRepository {
    @Throws(Exception::class)
    override suspend fun getLoginData(username: String, password: String): UserLogin? = withContext(Dispatchers.IO) {
        return@withContext localDataStore.getLoginData(username, password)
    }

    override suspend fun addUserData(username: String, password: String): String? = withContext(Dispatchers.IO) {
        return@withContext localDataStore.addUserData(username, password)
    }
}