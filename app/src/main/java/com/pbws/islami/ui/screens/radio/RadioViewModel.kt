package com.pbws.islami.ui.screens.radio

import android.net.http.HttpException
import android.net.http.NetworkException
import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pbws.domain.usecases.QuranSoundUseCase
import com.pbws.domain.usecases.RadioSoundUseCase
import com.pbws.islami.ui.screens.splash.SplashState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject

@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
@HiltViewModel
class RadioViewModel @Inject constructor(
    private val radioSoundsUseCase: RadioSoundUseCase,
    private val quranSoundsUseCase: QuranSoundUseCase
) : ViewModel() {
    var channel: Channel<RadioIntent> = Channel()
    private var _state = MutableStateFlow<RadioState>(RadioState.Ideal)
    var state = _state.asStateFlow()

    init {
        handelIntent()
    }

    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    private fun handelIntent() {
        viewModelScope.launch {
            channel.consumeAsFlow().collect { state ->
                when (state) {
                    RadioIntent.GetSounds -> handelGetSounds()
                }
            }
        }
    }

    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    private fun handelGetSounds() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _state.value = RadioState.Loading
                val quranSounds =
                    quranSoundsUseCase.invoke().audioFiles?.filterNotNull() ?: emptyList()
                val radioSounds = radioSoundsUseCase.invoke().radios?.filterNotNull() ?: emptyList()
                _state.value = RadioState.RadioAndQuranSoundsRetrievedSuccess(
                    radioSounds = radioSounds, quranSounds = quranSounds
                )
            } catch (ex: NetworkException) {
                _state.value = RadioState.Error(ex.localizedMessage)
            } catch (ex: HttpException) {
                _state.value = RadioState.Error(ex.localizedMessage)
            } catch (ex: IOException) {
                _state.value = RadioState.Error(ex.localizedMessage)
            } catch (ex: Exception) {
                _state.value = RadioState.Error(ex.localizedMessage)
            }
        }
    }

}