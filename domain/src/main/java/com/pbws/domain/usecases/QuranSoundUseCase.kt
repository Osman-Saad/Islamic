package com.pbws.domain.usecases

import com.pbws.domain.entity.soundentity.quran.QuranSound
import com.pbws.domain.repository.RadioRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class QuranSoundUseCase @Inject constructor(
    private val radioRepository: RadioRepository
) {

    suspend operator fun invoke():QuranSound {
        try {
            return radioRepository.getQuranSound()
        }catch (ex:Exception){
            throw ex
        }
    }
}