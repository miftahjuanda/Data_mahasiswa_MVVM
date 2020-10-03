package com.udacoding.mahasiswa_app_miftah.ui.register.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacoding.mahasiswa_app_miftah.model.getLogin.ResponseLogin
import com.udacoding.mahasiswa_app_miftah.repository.RepositoryRegister

class ViewModelRegister : ViewModel() {

    val repositoryRegister = RepositoryRegister()
    var response = MutableLiveData<ResponseLogin>()
    var onError = MutableLiveData<Throwable>()
    var passConfirm = MutableLiveData<String>()
    var passLengt = MutableLiveData<String>()
    var passEmpty = MutableLiveData<String>()
    var onIntent = MutableLiveData<Boolean>()

    fun register(nama: String, email: String, password: String, passwordConfirm: String) {

        onIntent.value = false

        if (nama.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty() && passwordConfirm.isNotEmpty()) {
            onIntent.value = false

            if (password != passwordConfirm) {
                onIntent.value = false
                passConfirm.value = "Kombinasi password tidak sama"

            } else if (password.length <= 6) {
                onIntent.value = false
                passLengt.value = "Password Minimal 6 Karakter"

            } else {
                onIntent.value = true
                repositoryRegister.getRegistrasi(nama, email, password, passwordConfirm,
                    {
                        response.value?.data
                    }, {
                        onError.value = it
                    })
            }
        } else {
            onIntent.value = false
            passEmpty.value = "Lengkapi Isian"
        }
    }
}