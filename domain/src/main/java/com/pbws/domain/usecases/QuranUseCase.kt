package com.pbws.domain.usecases

import com.pbws.domain.entity.quranentity.Quran
import com.pbws.domain.repository.quranrepo.QuranRepository
import javax.inject.Inject

class QuranUseCase @Inject constructor(private val quranRepository: QuranRepository) {
    suspend operator fun invoke():Quran{
        return quranRepository.getSuwar()
    }
}