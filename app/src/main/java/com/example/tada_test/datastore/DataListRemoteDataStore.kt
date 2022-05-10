package com.example.tada_test.datastore

import com.example.tada_test.model.DataResponse


interface DataListRemoteDataStore {
    suspend fun getDataList(dataSize: Int): DataResponse?
}