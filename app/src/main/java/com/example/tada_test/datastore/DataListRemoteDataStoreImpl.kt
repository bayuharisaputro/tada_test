package com.example.tada_test.datastore
import com.example.tada_test.api.ApiClient
import com.example.tada_test.api.ApiInterface
import com.example.tada_test.exception.ApiResponseException
import com.example.tada_test.model.DataResponse

import javax.inject.Inject
import kotlin.jvm.Throws


class DataListRemoteDataStoreImpl @Inject constructor(
        private val api: ApiInterface
) : DataListRemoteDataStore {

    @Throws(Exception::class)
    override suspend fun getDataList(dataSize: Int): DataResponse? {
        try {
            val apiRes = api.getDataList(apiKey = ApiClient.apiKey, dataSize).execute()
            try {
                if (apiRes.isSuccessful) {
                    return apiRes.body()
                } else {
                    throw ApiResponseException(apiRes.code().toString(), apiRes.message())
                }
            } catch (e: Exception) {
                throw e
            }
        } catch (e: java.lang.Exception) {
            throw e
        }
    }
}