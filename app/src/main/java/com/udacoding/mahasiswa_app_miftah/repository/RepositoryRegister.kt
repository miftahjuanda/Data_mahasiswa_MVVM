package com.udacoding.mahasiswa_app_miftah.repository

import com.udacoding.mahasiswa_app_miftah.model.getLogin.ResponseLogin
import com.udacoding.mahasiswa_app_miftah.network.NetworkModule
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class RepositoryRegister {

    fun getLogin(
        email: String, password: String,
        responseHandle: (ResponseLogin) -> Unit, errorHandler: (Throwable) -> Unit
    ) {

        NetworkModule.service().getLogin(email, password)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ response ->
                responseHandle(response)
            }, { error ->
                errorHandler(error)
            })
    }


    fun getRegistrasi(
        nama: String, email: String, password: String, passwordConfirm: String,
        responRegister: (ResponseLogin) -> Unit, errorHandler: (Throwable) -> Unit
    ) {

        NetworkModule.service().getRegister(nama, email, password)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ response ->
                responRegister(response)
            }, { error ->
                errorHandler(error)
            })
    }
}