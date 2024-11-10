package com.pbws.islami.ui.screens.praytimeazkar.azkar

import android.net.http.HttpException
import android.net.http.NetworkException
import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pbws.data.model.azkar.masaa.AzkarMassaItem
import com.pbws.data.model.azkar.sabah.AzkarSabahItem
import com.pbws.domain.usecases.AzkarMasaaUseCase
import com.pbws.domain.usecases.AzkarSabahUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch
import java.io.IOException
import java.net.SocketTimeoutException
import javax.inject.Inject

@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
@HiltViewModel
class AzkarViewModel @Inject constructor(
    private val azkarSabahUseCase: AzkarSabahUseCase,
    private val azkarMassaUseCase: AzkarMasaaUseCase
) : ViewModel() {
    var channel: Channel<AzkarIntent> = Channel()
    private var _state = MutableStateFlow<AzkarState>(AzkarState.Ideal)
    val state = _state.asStateFlow()

    init {
        handelIntent()
    }

    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    private fun handelIntent() {
        viewModelScope.launch {
            channel.consumeAsFlow().collect { intent ->
                when (intent) {
                    AzkarIntent.GetAzkarMassaa -> handelGetAzkarSabah()
                    AzkarIntent.GetAzkarSabah -> handelGetAzkarMassa()
                }
            }
        }
    }

    private fun handelGetAzkarMassa() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _state.value = AzkarState.Loading
                val azkar = azkarMassaUseCase.invoke()
                val azkarElMassaa: List<AzkarMassaItem> = azkar.content
                    ?.filterNotNull() ?: emptyList()
                _state.value = AzkarState.Success(
                    azkar = azkarElMassaa
                )
            } catch (ex: NetworkException) {
                _state.value = AzkarState.Error(ex.localizedMessage)
            } catch (ex: HttpException) {
                _state.value = AzkarState.Error(ex.localizedMessage)
            } catch (ex: IOException) {
                _state.value = AzkarState.Error(ex.localizedMessage)
            } catch (ex: SocketTimeoutException) {
                _state.value = AzkarState.Error(ex.localizedMessage)
            } catch (ex: Exception) {
                _state.value = AzkarState.Error(ex.localizedMessage)
            }
        }
    }

    private fun handelGetAzkarSabah() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _state.value = AzkarState.Loading
                val azkar = azkarSabahUseCase.invoke()
                val azkarElsabah: List<AzkarSabahItem> = azkar.content
                    ?.filterNotNull() ?: emptyList()
                _state.value = AzkarState.Success(
                    azkar = azkarElsabah
                )
            } catch (ex: NetworkException) {
                _state.value = AzkarState.Error(ex.localizedMessage)
            } catch (ex: HttpException) {
                _state.value = AzkarState.Error(ex.localizedMessage)
            } catch (ex: IOException) {
                _state.value = AzkarState.Error(ex.localizedMessage)
            } catch (ex: SocketTimeoutException) {
                _state.value = AzkarState.Error(ex.localizedMessage)
            } catch (ex: Exception) {
                _state.value = AzkarState.Error(ex.localizedMessage)
            }
        }
    }
}