package com.pbws.domain.usecases

import com.pbws.domain.entity.qurantafasirentity.QuranTafasir
import com.pbws.domain.repository.QuranTafasirRepository
import javax.inject.Inject

class QuranTafasirUseCase @Inject constructor(
    private val quranTafasirRepository: QuranTafasirRepository
) {

    suspend operator fun invoke(suraNumber:Int):QuranTafasir{
        try {
            return quranTafasirRepository.getQuranTafasir(suraNumber)
        }catch (ex:Exception){
            throw ex
        }
    }
}