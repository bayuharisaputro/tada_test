package com.example.tada_test.repository

import com.example.tada_test.datastore.DataListRemoteDataStore
import com.example.tada_test.exception.NoInternetException
import com.example.tada_test.model.DataResponse
import com.example.tada_test.util.ConnectionUtil
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.jvm.Throws

class DataListRepositoryImpl @Inject constructor(
    private val remoteDataStore: DataListRemoteDataStore,
    private val networkUtil: ConnectionUtil
) : DataListRepository {
    @Throws(Exception::class)
    override suspend fun getDataList(dataSize: Int): DataResponse? = withContext(Dispatchers.IO){
        if (networkUtil.isInternetConnected()) {
            return@withContext remoteDataStore.getDataList(dataSize)
        }
        else {
            throw NoInternetException()
        }
    }


}