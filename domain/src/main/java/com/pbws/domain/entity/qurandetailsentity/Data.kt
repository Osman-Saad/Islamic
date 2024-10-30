package com.pbws.domain.entity.qurandetailsentity


data class Data(
	val number: Int? = null,
	val englishName: String? = null,
	val numberOfAyahs: Int? = null,
	val name: String? = null,
	val ayahs: List<AyahsItem?>? = null,
)