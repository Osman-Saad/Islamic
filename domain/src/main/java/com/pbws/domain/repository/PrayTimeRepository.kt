package com.pbws.domain.repository

import com.pbws.domain.entity.praytimeentity.PrayTime

interface PrayTimeRepository {
    suspend fun getPrayTime(date:String,city:String): PrayTime
}