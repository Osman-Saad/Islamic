package com.pbws.islami.ui.screens.qurandetails

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pbws.domain.usecases.QuranDetailsUseCase
import com.pbws.domain.usecases.QuranTafasirUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuranDetailsViewModel @Inject constructor(
    private val quranDetailsUseCase: QuranDetailsUseCase,
    private val quranTafasirUseCase: QuranTafasirUseCase
) : ViewModel() {
    var channel: Channel<QuranDetailsIntent> = Channel()
    private var _state = MutableStateFlow<QuranDetailsState>(QuranDetailsState.Ideal)
    val state = _state.asStateFlow()

    init {
        handelIntent()
    }

    private fun handelIntent() {
        viewModelScope.launch {
            channel.consumeAsFlow().collect { intent ->
                when (intent) {
                    is QuranDetailsIntent.GetQuranDetails -> handelGetQuranDetails(intent.suraNumber)
                }
            }
        }
    }

    private fun handelGetQuranDetails(suraNumber: Int) {
        try {
            viewModelScope.launch(Dispatchers.IO) {
                _state.value = QuranDetailsState.Loading
                val quranDetails = quranDetailsUseCase.invoke(suraNumber)
                val quranTafasir = quranTafasirUseCase.invoke(suraNumber)
                Log.d("TA",quranTafasir.result.toString())
                _state.value = QuranDetailsState.Success(quranDetails,quranTafasir)
            }
        }catch (ex:Exception){
            _state.value = QuranDetailsState.Error(ex.localizedMessage)
        }
    }

}