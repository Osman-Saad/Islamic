package com.pbws.domain.usecases

import com.pbws.domain.entity.quranentity.Quran
import com.pbws.domain.repository.QuranRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class QuranUseCase @Inject constructor(private val quranRepository: QuranRepository) {
    suspend operator fun invoke(): Quran {
        try {
            return quranRepository.getSuwar()
        } catch (ex: Exception) {
            throw ex
        }
    }
}