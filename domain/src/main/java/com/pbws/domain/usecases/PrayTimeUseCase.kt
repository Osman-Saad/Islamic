package com.pbws.domain.usecases

import android.net.http.HttpException
import android.net.http.NetworkException
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresExtension
import com.pbws.domain.entity.praytimeentity.PrayTime
import com.pbws.domain.repository.PrayTimeRepository
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import java.io.IOException
import java.net.SocketTimeoutException
import javax.inject.Inject

class PrayTimeUseCase @Inject constructor(
    private val prayTimeRepository: PrayTimeRepository
) {
    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    suspend operator fun invoke(city: String): PrayTime {
        try {
            Log.d("TX",getCurrentDate())
            return prayTimeRepository.getPrayTime(getCurrentDate(), city)
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

    private fun getCurrentDate(): String {
        val currentInstant = Clock.System.now()
        val localDateTime = currentInstant.toLocalDateTime(TimeZone.currentSystemDefault())
        val localDate: LocalDate = localDateTime.date
        val day = localDate.dayOfMonth.toString().padStart(2, '0')
        val month = localDate.monthNumber.toString().padStart(2, '0')
        val year = localDate.year.toString()
        return "$day-$month-$year"
    }
}