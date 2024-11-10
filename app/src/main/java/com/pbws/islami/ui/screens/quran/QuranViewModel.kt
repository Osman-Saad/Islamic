package com.pbws.islami.ui.screens.quran

import android.net.http.HttpException
import android.net.http.NetworkException
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresExtension
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pbws.domain.usecases.PrayTimeUseCase
import com.pbws.domain.usecases.QuranUseCase
import com.pbws.islami.ui.screens.praytimeazkar.initialPrayTime
import com.pbws.islami.ui.screens.qurandetails.QuranDetailsState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject

@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
@HiltViewModel
class QuranViewModel @Inject constructor(
    private val quranUseCase: QuranUseCase,
    private val prayTimeUseCase: PrayTimeUseCase
) : ViewModel() {
    var channel: Channel<QuranIntent> = Channel()
    private var _state: MutableSharedFlow<QuranState> = MutableSharedFlow(replay = 0)
    var state = _state.asSharedFlow()

    init {
        handelIntent()
    }

    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    private fun handelIntent() {
        viewModelScope.launch {
            channel.consumeAsFlow().collect { intent ->
                when (intent) {
                    QuranIntent.GetQuran -> handelGetQuran()
                }
            }
        }
    }



    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    private fun handelGetQuran() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _state.emit(QuranState.Loading)
                val quran = quranUseCase.invoke()
                val suwarList = quran.suwar?.filterNotNull() ?: emptyList()
                _state.emit(QuranState.Success(suwarList))
            } catch (ex: NetworkException) {
                _state.emit(QuranState.Error(ex.localizedMessage))
            } catch (ex: HttpException) {
                _state.emit(QuranState.Error(ex.localizedMessage))
            } catch (ex: IOException) {
                _state.emit(QuranState.Error(ex.localizedMessage))
            } catch (ex: Exception) {
                Log.d("TAG", ex.localizedMessage)
                _state.emit(QuranState.Error(ex.localizedMessage))
            }
        }
    }
}