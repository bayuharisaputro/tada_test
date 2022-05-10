package com.example.tada_test.repository

import com.example.tada_test.model.UserLogin

interface LoginAndRegisterRepository {
    suspend fun getLoginData(username : String, password: String): UserLogin?

    suspend fun addUserData(username: String, password: String): String?
}