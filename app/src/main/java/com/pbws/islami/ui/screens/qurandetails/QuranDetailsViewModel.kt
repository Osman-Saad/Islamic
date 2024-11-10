package com.pbws.islami.ui.screens.qurandetails

import android.net.http.HttpException
import android.net.http.NetworkException
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresExtension
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pbws.domain.usecases.QuranDetailsUseCase
import com.pbws.domain.usecases.QuranTafasirUseCase
import com.pbws.islami.ui.screens.radio.RadioState
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

    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    private fun handelIntent() {
        viewModelScope.launch {
            channel.consumeAsFlow().collect { intent ->
                when (intent) {
                    is QuranDetailsIntent.GetQuranDetails -> handelGetQuranDetails(intent.suraNumber)
                }
            }
        }
    }

    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    private fun handelGetQuranDetails(suraNumber: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _state.value = QuranDetailsState.Loading
                val quranDetails = quranDetailsUseCase.invoke(suraNumber)
                val quranTafasir = quranTafasirUseCase.invoke(suraNumber)
                Log.d("TA", quranTafasir.result.toString())
                _state.value = QuranDetailsState.Success(quranDetails, quranTafasir)

            } catch (ex: NetworkException) {
                _state.value = QuranDetailsState.Error(ex.localizedMessage)
            } catch (ex: HttpException) {
                _state.value = QuranDetailsState.Error(ex.localizedMessage)
            } catch (ex: IOException) {
                _state.value = QuranDetailsState.Error(ex.localizedMessage)
            } catch (ex: Exception) {
                _state.value = QuranDetailsState.Error(ex.localizedMessage)
            }
        }
    }

}