package com.pbws.data.model.quranmodel

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class SuwarItemDto(

//	@field:SerializedName("end_page")
//	val endPage: Int? = null,
//
//	@field:SerializedName("start_page")
//	val startPage: Int? = null,

	@field:SerializedName("makkia")
	val makkia: Int? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("type")
	val type: Int? = null
)