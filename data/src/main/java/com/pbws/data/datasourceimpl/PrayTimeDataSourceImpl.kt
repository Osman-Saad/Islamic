package com.pbws.data.datasourceimpl

import android.net.http.HttpException
import android.net.http.NetworkException
import android.os.Build
import androidx.annotation.RequiresExtension
import com.pbws.data.datasource.PrayTimeDataSource
import com.pbws.data.di.PrayTimeApiManager
import com.pbws.data.model.praytime.PrayTimeDto
import com.pbws.data.remote.ApiManager
import java.io.IOException
import java.net.SocketTimeoutException
import javax.inject.Inject

class PrayTimeDataSourceImpl @Inject constructor(
    @PrayTimeApiManager private val apiManager: ApiManager
):PrayTimeDataSource {
    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    override suspend fun getPrayTime(date: String, city: String): PrayTimeDto {
        try {
            return apiManager.getPrayTime(date, city)
        }catch (ex:SocketTimeoutException){
            throw ex
        }catch (ex:HttpException){
            throw ex
        }catch (ex:NetworkException){
            throw ex
        }catch (ex:IOException){
            throw ex
        }catch (ex:Exception){
            throw ex
        }
    }
}