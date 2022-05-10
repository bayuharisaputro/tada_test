package com.example.tada_test.model

data class UserLogin (
    var id: String = "",
    var username: String = "",
    var password: String = ""

) {
    override fun toString(): String {
        return "UserLogin(id='$id', username='$username', password='$password')"
    }
}