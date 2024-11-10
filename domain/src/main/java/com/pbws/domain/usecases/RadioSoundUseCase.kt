package com.pbws.domain.usecases

import com.pbws.domain.entity.soundentity.radio.RadioSound
import com.pbws.domain.repository.RadioRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RadioSoundUseCase @Inject constructor(
    private val radioRepository: RadioRepository
) {

    suspend operator fun invoke():RadioSound{
        try {
            return radioRepository.getRadioSound()
        }catch (ex:Exception){
            throw ex
        }
    }
}