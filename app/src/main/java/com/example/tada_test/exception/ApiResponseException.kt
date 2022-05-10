package com.example.tada_test.exception

class ApiResponseException(val errorCode: String?, val errorMessage: String?) : Exception()