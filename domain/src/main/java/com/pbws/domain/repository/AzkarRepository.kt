package com.pbws.domain.repository

import com.pbws.data.model.azkar.masaa.AzkarMasaa
import com.pbws.data.model.azkar.sabah.AzkarSabah

interface AzkarRepository {
    suspend fun getAzkarSabah(): AzkarSabah
    suspend fun getAzkarMassaa(): AzkarMasaa
}