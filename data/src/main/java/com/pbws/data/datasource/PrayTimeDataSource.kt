package com.pbws.data.datasource

import com.pbws.data.model.praytime.PrayTimeDto

interface PrayTimeDataSource {
    suspend fun getPrayTime(date:String,city:String): PrayTimeDto
}