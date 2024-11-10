package com.pbws.data.datasource

import com.pbws.data.model.azkar.masaa.AzkarMasaa
import com.pbws.data.model.azkar.masaa.AzkarMasaaDto
import com.pbws.data.model.azkar.sabah.AzkarSabah
import com.pbws.data.model.azkar.sabah.AzkarSabahDto

interface AzkarDataSource {

    suspend fun getAzkarSabah():AzkarSabahDto
    suspend fun getAzkarMassaa():AzkarMasaaDto
}