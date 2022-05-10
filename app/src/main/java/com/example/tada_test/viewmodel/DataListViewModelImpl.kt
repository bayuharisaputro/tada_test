package com.example.tada_test.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.tada_test.model.ArtObjects
import com.example.tada_test.repository.DataListRepository
import kotlinx.coroutines.launch
import javax.inject.Inject


class DataListViewModelImpl @Inject constructor(
        private val repository: DataListRepository
) : DataListViewModel() {

    private val mIsLoading: MutableLiveData<Boolean> = MutableLiveData(false)
    private val mErrorHandler: MutableLiveData<Exception?> = MutableLiveData()
    private val mDataList: MutableLiveData<ArrayList<ArtObjects>?> = MutableLiveData()

    override val ldIsLoading: LiveData<Boolean> = mIsLoading
    override val ldErrorHandler: LiveData<Exception?> = mErrorHandler
    override val ldDataList: LiveData<ArrayList<ArtObjects>?> = mDataList


    override fun requestGetDataList(dataSize: Int) {
        viewModelScope.launch {
            mIsLoading.value = true
            try {
                mDataList.value = repository.getDataList(dataSize)?.artObjects
                mErrorHandler.value = null
            }
            catch (e: Exception) {
                mDataList.value = null
                mErrorHandler.value = e
            }
            finally {
                mIsLoading.value = false
            }
        }
    }
}