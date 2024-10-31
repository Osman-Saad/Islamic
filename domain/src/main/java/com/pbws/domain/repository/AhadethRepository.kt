package com.pbws.domain.repository

import androidx.paging.PagingData
import com.pbws.domain.entity.ahadethentity.Ahadeth
import com.pbws.domain.entity.ahadethentity.AhadethItem
import kotlinx.coroutines.flow.Flow

interface AhadethRepository {
    suspend fun getAhadeth(): Flow<PagingData<AhadethItem>>
}