package com.udacoding.mahasiswa_app_miftah.network

import com.udacoding.mahasiswa_app_miftah.model.action.ResponseAction
import com.udacoding.mahasiswa_app_miftah.model.getData.ResponseGetData
import com.udacoding.mahasiswa_app_miftah.model.getLogin.ResponseLogin
import com.udacoding.mahasiswa_app_miftah.model.getRegister.ResponseRegister
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Single
import retrofit2.Call
import retrofit2.http.*

interface ApiService {

    //todo 3 membuat request ke server

    //getData
    @GET("getData.php")
    fun getData(): Flowable<ResponseGetData>

    //insert
    @FormUrlEncoded
    @POST("insert.php")
    fun insertData(@Field("mahasiswa_nama") mahasiswa_nama : String,
                    @Field("mahasiswa_nohp") mahasiswa_nohp : String,
                    @Field("mahasiswa_alamat") mahasiswa_alamat : String ) : Flowable<ResponseAction>

    //update
    @FormUrlEncoded
    @POST("update.php")
    fun updateData(@Field("id_mahasiswa") id_mahasiswa : String,
                   @Field("mahasiswa_nama") mahasiswa_nama : String,
                   @Field("mahasiswa_nohp") mahasiswa_nohp : String,
                   @Field("mahasiswa_alamat") mahasiswa_alamat : String ) : Flowable<ResponseAction>

    //delete
    @FormUrlEncoded
    @POST("delete.php")
    fun deleteData(@Field("id_mahasiswa") id_mahasiswa : String) : Flowable<ResponseAction>

    //Register
    @FormUrlEncoded
    @POST("register.php")
    fun getRegister(@Field("user_nama") user_nama: String,
                    @Field("user_email") user_email: String,
                    @Field("user_password") user_password: String
    ) : Single<ResponseLogin>

    //Login
    @FormUrlEncoded
    @POST("login.php")
    fun getLogin(@Field("user_email") user_email: String,
                 @Field("user_password") user_password: String
    ) : Single<ResponseLogin>

}