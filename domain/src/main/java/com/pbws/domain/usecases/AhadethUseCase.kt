package com.pbws.domain.usecases

import androidx.paging.PagingData
import com.pbws.domain.entity.ahadethentity.Ahadeth
import com.pbws.domain.entity.ahadethentity.AhadethItem
import com.pbws.domain.repository.AhadethRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AhadethUseCase @Inject constructor(
    private val ahadethRepository: AhadethRepository
) {
    suspend fun getAhadeth(): Flow<PagingData<AhadethItem>> {
        try {
            return ahadethRepository.getAhadeth()
        }catch (ex:Exception){
            throw ex
        }
    }
}