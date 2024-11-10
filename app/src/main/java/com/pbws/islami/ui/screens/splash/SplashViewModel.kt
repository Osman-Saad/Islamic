package com.pbws.islami.ui.screens.splash

import android.net.http.HttpException
import android.net.http.NetworkException
import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pbws.domain.usecases.PrayTimeUseCase
import com.pbws.domain.usecases.QuranUseCase
import com.pbws.islami.ui.screens.praytimeazkar.initialPrayTime
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
class SplashViewModel @Inject constructor(
    private val quranUseCase: QuranUseCase,
    private val prayTimeUseCase: PrayTimeUseCase
):ViewModel() {

    var channel: Channel<SplashIntent> = Channel()
    private var _state: MutableStateFlow<SplashState> = MutableStateFlow(SplashState.Ideal)
    var state = _state.asStateFlow()

    init {
        handelIntent()
    }

    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    private fun handelIntent() {
        viewModelScope.launch {
            channel.consumeAsFlow().collect { intent ->
                when (intent) {
                    is SplashIntent.GetPrayTime -> handelGetPrayTime(intent.city)
                    SplashIntent.GetSuwar -> handelGetSuwar()
                }
            }
        }
    }

    private fun handelGetSuwar() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _state.value = SplashState.Loading
                val quran = quranUseCase.invoke()
                val suwarList = quran.suwar?.filterNotNull() ?: emptyList()
                _state.value = SplashState.Success(suwarList)
            }catch (ex:SocketTimeoutException){
                _state.value = SplashState.Error(ex.localizedMessage)
            } catch (ex:NetworkException){
                _state.value = SplashState.Error(ex.localizedMessage)
            }catch (ex:HttpException){
                _state.value = SplashState.Error(ex.localizedMessage)
            }catch (ex:IOException){
                _state.value = SplashState.Error(ex.localizedMessage)
            } catch (ex: Exception) {
                _state.value = SplashState.Error(ex.localizedMessage)
            }
        }
    }

    private fun handelGetPrayTime(city: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val prayTime = prayTimeUseCase.invoke(city)
                initialPrayTime = prayTime
            }catch (ex:SocketTimeoutException){
                _state.value = SplashState.Error(ex.localizedMessage)
            } catch (ex:NetworkException){
                _state.value = SplashState.Error(ex.localizedMessage)
            }catch (ex:HttpException){
                _state.value = SplashState.Error(ex.localizedMessage)
            }catch (ex:IOException){
                _state.value = SplashState.Error(ex.localizedMessage)
            } catch (ex: Exception) {
                _state.value = SplashState.Error(ex.localizedMessage)
            }
        }

    }
}