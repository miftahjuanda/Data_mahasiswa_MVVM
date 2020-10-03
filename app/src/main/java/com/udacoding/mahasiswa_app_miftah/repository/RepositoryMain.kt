package com.udacoding.mahasiswa_app_miftah.repository

import com.udacoding.mahasiswa_app_miftah.model.getData.ResponseGetData
import com.udacoding.mahasiswa_app_miftah.network.NetworkModule
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class RepositoryMain {

    fun getData(responHandle: (ResponseGetData) -> Unit, errorHandler: (Throwable) -> Unit) {
        NetworkModule.service().getData().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ it ->
                responHandle(it)
            }, { error ->
                errorHandler(error)
            })
    }

    fun getDelete(id: String) {
        val delete = NetworkModule.service().deleteData(id ?: "")
        delete.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({

            }, {

            })
    }

    fun getInput(nama: String, nohp: String, alamat: String) {

        NetworkModule.service().insertData(nama, nohp, alamat)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({

            }, {

            })
    }

    fun getUpdate(id: String, nama: String, nohp: String, alamat: String) {

        NetworkModule.service().updateData(id ?: "", nama ?: "", nohp ?: "", alamat ?: "")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({

            }, {

            })
    }
}