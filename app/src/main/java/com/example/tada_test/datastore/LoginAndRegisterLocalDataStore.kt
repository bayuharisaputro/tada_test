package com.example.tada_test.datastore

import com.example.tada_test.model.UserLogin

interface LoginAndRegisterLocalDataStore {
    suspend fun getLoginData(username: String, password: String): UserLogin?

    suspend fun addUserData(username: String, password: String): String?
}