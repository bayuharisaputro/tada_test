package com.example.tada_test.viewmodel

import android.os.Build
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.tada_test.model.DataResponse
import com.example.tada_test.repository.DataListRepositoryImpl
import com.google.common.truth.Truth
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule

import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.robolectric.annotation.Config
import java.io.IOException

@Config(maxSdk = Build.VERSION_CODES.P)
@RunWith(AndroidJUnit4::class)
class DataListViewModelImplTest {
    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    @Mock
    var repo : DataListRepositoryImpl? = null

    @Before
    fun init(){
        MockitoAnnotations.openMocks(this)
    }

    @Test
    fun requestGetDataList() {
        runBlocking {
            val vm = DataListViewModelImpl(repo!!)
            Mockito.`when`(repo?.getDataList(50)).thenReturn(DataResponse())

            vm.requestGetDataList(50)

            val dataList = vm.ldDataList.value
            val error = vm.ldErrorHandler.value
            val isLoading = vm.ldIsLoading.value
            Truth.assertThat(dataList).isNotNull()
            Truth.assertThat(error).isNull()
            Truth.assertThat(isLoading).isFalse()
        }
    }

    @Test
    fun requestGetDataListError() {
        runBlocking {
            val vm = DataListViewModelImpl(repo!!)
            Mockito.`when`(repo?.getDataList(50)).thenThrow(IOException("error"))

            vm.requestGetDataList(50)

            val dataList = vm.ldDataList.value
            val error = vm.ldErrorHandler.value
            val isLoading = vm.ldIsLoading.value
            Truth.assertThat(dataList).isNull()
            Truth.assertThat(error).isNotNull()
            Truth.assertThat(error!!.message).isEqualTo("error")
            Truth.assertThat(isLoading).isFalse()
        }
    }
}