package com.pbws.islami.ui.screens.praytimeazkar.praytime

import android.net.http.HttpException
import android.net.http.NetworkException
import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pbws.domain.usecases.PrayTimeUseCase
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
class PrayTimeViewModel @Inject constructor(
    private val prayTimeUseCase: PrayTimeUseCase
):ViewModel() {
    var channel: Channel<PrayTimeIntent> = Channel()
    private var _state = MutableStateFlow<PrayTimeState>(PrayTimeState.Ideal)
    var state = _state.asStateFlow()

    init {
        handelIntent()
    }

    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    private fun handelIntent() {
        viewModelScope.launch {
            channel.consumeAsFlow().collect { intent ->
                when (intent) {
                    is PrayTimeIntent.GetPrayTime -> handelGetPrayTime(intent.city)
                }
            }
        }
    }

    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    private fun handelGetPrayTime(city: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _state.value = PrayTimeState.Loading
                val prayTime = prayTimeUseCase.invoke(city)
                _state.value = PrayTimeState.Success(prayTime)
            }catch (ex: SocketTimeoutException){
                _state.value = PrayTimeState.Error(ex.localizedMessage)
            } catch (ex: NetworkException){
                _state.value = PrayTimeState.Error(ex.localizedMessage)
            }catch (ex: HttpException){
                _state.value = PrayTimeState.Error(ex.localizedMessage)
            }catch (ex: IOException){
                _state.value = PrayTimeState.Error(ex.localizedMessage)
            } catch (ex: Exception) {
                _state.value = PrayTimeState.Error(ex.localizedMessage)
            }
        }
    }
}