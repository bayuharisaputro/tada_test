package com.example.tada_test.datastore

import com.example.tada_test.api.ApiClient
import com.example.tada_test.api.ApiInterface
import com.example.tada_test.model.DataResponse
import com.google.common.truth.Truth
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import retrofit2.Call
import retrofit2.Response
import java.io.IOException

class DataListRemoteDataStoreImplTest {
    @Mock
    val apiInterface : ApiInterface? = null

    @Mock
    val response : Response<DataResponse>? = null

    @Mock
    val callRetrofit : Call<DataResponse>? = null

    @Before
    fun init(){
        MockitoAnnotations.openMocks(this)
        Mockito.`when`(apiInterface?.getDataList(ApiClient.apiKey, 50)).thenReturn(callRetrofit)
    }

    @Test
    fun getDataList() {
        runBlocking {
            Mockito.`when`(callRetrofit?.execute()).thenReturn(response)
            Mockito.`when`(response?.body()).thenReturn(DataResponse())
            Mockito.`when`(response?.isSuccessful).thenReturn(true)
            val remoteGetData = DataListRemoteDataStoreImpl(apiInterface!!)
            val res = remoteGetData.getDataList(50)
            Truth.assertThat(res).isNotNull()
        }
    }

    @Test
    fun getDataListError() {
        runBlocking {
            Mockito.`when`(callRetrofit?.execute()).thenThrow(IOException("Error"))
            val remoteGetData = DataListRemoteDataStoreImpl(apiInterface!!)
            try{
                remoteGetData.getDataList(50)
                throw RuntimeException("Is must not be here")
            }
            catch (e : Exception){
                Truth.assertThat(e.message).isEqualTo("Error")
            }
        }
    }

}