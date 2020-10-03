package com.udacoding.mahasiswa_app_miftah.ui.login.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacoding.mahasiswa_app_miftah.model.getLogin.DataUser
import com.udacoding.mahasiswa_app_miftah.model.getLogin.ResponseLogin
import com.udacoding.mahasiswa_app_miftah.repository.RepositoryRegister

class ViewModelLogin : ViewModel() {

    val repository = RepositoryRegister()

    var response = MutableLiveData<ResponseLogin>()
    var onError = MutableLiveData<Throwable>()
    var isLoading = MutableLiveData<Boolean>()
    var isEmpty = MutableLiveData<String>()

    fun login(email: String, password: String) {
        isLoading.value = false

        if (email.isNotEmpty() && password.isNotEmpty()) {
            repository.getLogin(email, password, {
                response.value = it ; isLoading.value = true

            }, {
                onError.value = it; isLoading.value = false
            })

            isLoading.value = false

        } else {
            isLoading.value = false
            isEmpty.value = "Isian Harus di isi"
        }
    }
}