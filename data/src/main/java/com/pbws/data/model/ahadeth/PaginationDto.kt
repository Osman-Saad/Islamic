package com.pbws.data.model.ahadeth

import kotlinx.serialization.Serializable

@Serializable
data class PaginationDto(
	val startPage: Int? = null,
	val startIndex: Int? = null,
	val endIndex: Int? = null,
	val pageSize: Int? = null,
	val endPage: Int? = null,
	val currentPage: Int? = null
)