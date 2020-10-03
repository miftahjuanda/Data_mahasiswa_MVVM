package com.udacoding.mahasiswa_app_miftah.model.getLogin

import com.google.gson.annotations.SerializedName

data class ResponseLogin(

	@field:SerializedName("data")
	val data: List<DataUser>? = null,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("isSuccess")
	val isSuccess: Boolean? = null
)

data class DataUser(

	@field:SerializedName("user_nama")
	val userNama: String? = null,

	@field:SerializedName("user_email")
	val userEmail: String? = null,

	@field:SerializedName("user_password")
	val userPassword: String? = null,

	@field:SerializedName("user_id")
	val userId: String? = null
)
