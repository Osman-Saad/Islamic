package com.pbws.islami.ui.screens.ahadeth

sealed class AhadethIntent {
    data object GetAhadeth:AhadethIntent()
}