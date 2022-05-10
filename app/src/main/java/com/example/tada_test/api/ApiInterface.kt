package com.example.tada_test.api

import com.example.tada_test.model.DataResponse
import retrofit2.Call
import retrofit2.http.*


interface ApiInterface {

    @GET("collection")
    fun getDataList(@Query("key") apiKey: String,
                    @Query("ps") pageSize: Int): Call<DataResponse>
}