package com.pbws.domain.entity.ahadethentity


data class Pagination(
	val startPage: Int? = null,
	val startIndex: Int? = null,
	val endIndex: Int? = null,
	val pageSize: Int? = null,
	val endPage: Int? = null,
	val currentPage: Int? = null
)