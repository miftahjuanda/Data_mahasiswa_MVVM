package com.udacoding.mahasiswa_app_miftah.model.getData

import com.google.gson.annotations.SerializedName

data class ResponseGetData(

	//todo 2 generate data servernya menggunakan pojo generator
	@field:SerializedName("data")
	val data: List<DataItem>? = null,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("isSuccess")
	val isSuccess: Boolean? = null
)