package com.pbws.data.repositoryimpl

import android.net.http.HttpException
import android.net.http.NetworkException
import android.os.Build
import androidx.annotation.RequiresExtension
import com.pbws.data.constant.fromJson
import com.pbws.data.datasource.PrayTimeDataSource
import com.pbws.domain.entity.praytimeentity.PrayTime
import com.pbws.domain.repository.PrayTimeRepository
import java.io.IOException
import java.net.SocketTimeoutException
import javax.inject.Inject

class PrayTimeRepositoryImpl @Inject constructor(
    private val prayTimeDataSource: PrayTimeDataSource
) : PrayTimeRepository {
    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    override suspend fun getPrayTime(date: String, city: String): PrayTime {
        try {
            return prayTimeDataSource.getPrayTime(date, city).fromJson(PrayTime::class.java)
        } catch (ex: SocketTimeoutException) {
            throw ex
        } catch (ex: HttpException) {
            throw ex
        } catch (ex: NetworkException) {
            throw ex
        } catch (ex: IOException) {
            throw ex
        } catch (ex: Exception) {
            throw ex
        }
    }
}