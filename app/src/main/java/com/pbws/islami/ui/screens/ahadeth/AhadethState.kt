package com.pbws.islami.ui.screens.ahadeth

import androidx.paging.PagingData
import com.pbws.data.model.ahadeth.AhadethItemDto
import com.pbws.domain.entity.ahadethentity.AhadethItem
import kotlinx.coroutines.flow.MutableStateFlow

sealed class AhadethState {
    data object Ideal : AhadethState()
    data object Loading:AhadethState()
    data object Success : AhadethState()
    data class Error(val message: String) : AhadethState()
}