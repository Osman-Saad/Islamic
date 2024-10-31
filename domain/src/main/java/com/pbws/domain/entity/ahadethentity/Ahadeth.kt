package com.pbws.domain.entity.ahadethentity


data class Ahadeth(
	val pagination: Pagination? = null,
	val ahadethItems: List<AhadethItem?>? = null,
)