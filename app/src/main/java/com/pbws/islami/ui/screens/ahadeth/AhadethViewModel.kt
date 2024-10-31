package com.pbws.islami.ui.screens.ahadeth

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.pbws.data.constant.fromJson
import com.pbws.data.model.ahadeth.AhadethItemDto
import com.pbws.domain.entity.ahadethentity.AhadethItem
import com.pbws.domain.usecases.AhadethUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AhadethViewModel @Inject constructor (
    private val ahadethUseCase: AhadethUseCase
):ViewModel() {
    var channel:Channel<AhadethIntent> = Channel()
    private var _state = MutableStateFlow<AhadethState>(AhadethState.Ideal)
    var state = _state.asStateFlow()

    private var _ahadeth:MutableStateFlow<PagingData<AhadethItem>> = MutableStateFlow(PagingData.empty())
    val ahadeth = _ahadeth

    init {
        handelIntent()
    }

    private fun handelIntent() {
        viewModelScope.launch {
            channel.consumeAsFlow().collect{intent->
                when(intent){
                    AhadethIntent.GetAhadeth -> handelGetAhadeth()
                }
            }
        }
    }

    private fun handelGetAhadeth() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _state.value = AhadethState.Loading
                val ahadeth = ahadethUseCase.getAhadeth()
                ahadeth.cachedIn(viewModelScope).collect{
                    _ahadeth.value = it
                    _state.value = AhadethState.Success
                }
            }catch (ex:Exception){
                _state.value = AhadethState.Error(ex.localizedMessage)
            }
        }
    }
}