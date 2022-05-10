package com.example.tada_test.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.tada_test.model.ArtObjects
import java.util.ArrayList

abstract class DataListViewModel : ViewModel() {

    abstract val ldIsLoading: LiveData<Boolean>

    abstract val ldErrorHandler: LiveData<Exception?>

    abstract val ldDataList: LiveData<ArrayList<ArtObjects>?>

    abstract fun requestGetDataList(dataSize: Int)

}