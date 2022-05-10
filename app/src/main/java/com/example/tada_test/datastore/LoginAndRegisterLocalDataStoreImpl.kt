package com.example.tada_test.datastore

import com.example.tada_test.DBHelper
import com.example.tada_test.model.UserLogin

import javax.inject.Inject
import kotlin.jvm.Throws


class LoginAndRegisterLocalDataStoreImpl @Inject constructor(
    private val localDBHelper: DBHelper
) : LoginAndRegisterLocalDataStore {

    @Throws(Exception::class)
    override suspend fun getLoginData(username: String, password: String): UserLogin? {
        try {
            if (localDBHelper.getUser(username, password).isEmpty()) {
                throw java.lang.Exception("Invalid username or password")
            } else {
                return localDBHelper.getUser(username, password)[0]
            }

        } catch (e: java.lang.Exception) {
            throw e
        }
    }

    override suspend fun addUserData(username: String, password: String): String? {
        return localDBHelper.addUser(username, password)
    }
}