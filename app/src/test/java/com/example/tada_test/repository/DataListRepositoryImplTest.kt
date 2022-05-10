package com.example.tada_test.repository

import com.example.tada_test.datastore.DataListRemoteDataStoreImpl
import com.example.tada_test.exception.NoInternetException
import com.example.tada_test.model.DataResponse
import com.example.tada_test.util.ConnectionUtil
import com.google.common.truth.Truth
import kotlinx.coroutines.runBlocking
import org.junit.Before

import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class DataListRepositoryImplTest {
    @Mock
    var remoteDataStoreImpl : DataListRemoteDataStoreImpl? = null
    @Mock
    var netUtil : ConnectionUtil? = null

    @Before
    fun init(){
        MockitoAnnotations.openMocks(this)
    }

    @Test
    fun getDataList() {
        runBlocking {
            Mockito.`when`(remoteDataStoreImpl?.getDataList(50)).thenReturn(DataResponse())
            Mockito.`when`(netUtil?.isInternetConnected()).thenReturn(true)
            val getDataRepository : DataListRepositoryImpl? = DataListRepositoryImpl(remoteDataStoreImpl!!, netUtil!!)

            val res = getDataRepository?.getDataList(50)
            Truth.assertThat(res).isNotNull()
        }
    }

    @Test
    fun getDataListError() {
        runBlocking {
            Mockito.`when`(remoteDataStoreImpl?.getDataList(50)).thenReturn(DataResponse())
            Mockito.`when`(netUtil?.isInternetConnected()).thenReturn(false)
            val getDataRepository : DataListRepositoryImpl? = DataListRepositoryImpl(remoteDataStoreImpl!!, netUtil!!)

            try {
                getDataRepository?.getDataList(50)
                throw RuntimeException("Is must not be here")
            }
            catch (e : NoInternetException) {
                //expected to throw No Internet Exception
            }
        }
    }
}