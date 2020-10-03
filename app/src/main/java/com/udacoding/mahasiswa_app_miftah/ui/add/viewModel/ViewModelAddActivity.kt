package com.udacoding.mahasiswa_app_miftah.ui.add.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacoding.mahasiswa_app_miftah.model.action.ResponseAction
import com.udacoding.mahasiswa_app_miftah.model.getData.ResponseGetData
import com.udacoding.mahasiswa_app_miftah.repository.RepositoryMain

class ViewModelAddActivity : ViewModel() {

    val repository = RepositoryMain()

    var response = MutableLiveData<ResponseAction>()
    var isError = MutableLiveData<Throwable>()

    fun getInput(nama: String, nohp: String, alamat: String) {
        repository.getInput(nama, nohp, alamat)
    }

    fun getUpdate(id: String, nama: String, nohp: String, alamat: String) {
        repository.getUpdate(id, nama, nohp, alamat)
    }

}