package com.example.tada_test.repository

import com.example.tada_test.model.DataResponse

interface DataListRepository {
    suspend fun getDataList(dataSize: Int): DataResponse?
}