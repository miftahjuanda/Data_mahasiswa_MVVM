package com.udacoding.mahasiswa_app_miftah.ui.beranda.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacoding.mahasiswa_app_miftah.model.getData.ResponseGetData
import com.udacoding.mahasiswa_app_miftah.repository.RepositoryMain

class ViewModelMainActivity : ViewModel() {

    val repository = RepositoryMain()

    var responseGetData = MutableLiveData<ResponseGetData>()
    var isError = MutableLiveData<Throwable>()

    fun getData() {
        repository.getData({
            responseGetData.value = it
        }, {
            isError.value = it
        })
    }

    fun getDelete(id: String) {
        repository.getDelete(id)
    }
}