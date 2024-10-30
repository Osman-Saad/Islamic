package com.pbws.islami.ui.screens.quran

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pbws.domain.usecases.QuranUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuranViewModel @Inject constructor(
    private val quranUseCase: QuranUseCase
):ViewModel() {
    var channel:Channel<QuranIntent> = Channel()
    private var _state:MutableSharedFlow<QuranState> = MutableSharedFlow(replay = 0)
    var state = _state.asSharedFlow()

    init {
        handelIntent()
    }

    private fun handelIntent() {
        viewModelScope.launch {
            channel.consumeAsFlow().collect{intent->
                when(intent){
                    QuranIntent.GetQuran -> handelGetQuran()
                }
            }
        }
    }

    private fun handelGetQuran() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _state.emit(QuranState.Loading)
                val quran = quranUseCase.invoke()
                val suwarList = quran.suwar?.filterNotNull() ?: emptyList()
                _state.emit(QuranState.Success(suwarList))
            }catch (ex:Exception){
                Log.d("TAG",ex.localizedMessage)
                _state.emit(QuranState.Error(ex.localizedMessage))
            }
        }
    }
}