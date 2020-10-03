package com.udacoding.mahasiswa_app_miftah.model.action

import com.google.gson.annotations.SerializedName

data class ResponseAction(

	//todo convert respon server

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("isSuccess")
	val isSuccess: Boolean? = null
)