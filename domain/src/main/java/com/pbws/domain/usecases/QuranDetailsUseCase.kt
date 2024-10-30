package com.pbws.domain.usecases

import com.pbws.domain.entity.qurandetailsentity.Data
import com.pbws.domain.entity.qurandetailsentity.QuranDetails
import com.pbws.domain.repository.QuranDetailsRepository
import javax.inject.Inject

class QuranDetailsUseCase @Inject constructor(
    private val quranDetailsRepository: QuranDetailsRepository
) {

    suspend operator fun invoke(suraNumber:Int): QuranDetails {
        try {
            return quranDetailsRepository.getQuranDetails(suraNumber)
        }catch (ex:Exception){
            throw ex
        }
    }
}