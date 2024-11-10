package com.pbws.islami.ui.screens.praytimeazkar.azkar

sealed class AzkarIntent {
    data object GetAzkarSabah: AzkarIntent()
    data object GetAzkarMassaa: AzkarIntent()
}