package com.pbws.data.model.ahadeth

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AhadethDto(
	val paginationDto: PaginationDto? = null,
	@SerialName("items")
	val ahadethItems: List<AhadethItemDto?>? = null,
)