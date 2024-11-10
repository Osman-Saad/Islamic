package com.pbws.domain.usecases

import android.net.http.HttpException
import android.net.http.NetworkException
import android.os.Build
import androidx.annotation.RequiresExtension
import com.pbws.data.model.azkar.masaa.AzkarMasaa
import com.pbws.domain.repository.AzkarRepository
import java.io.IOException
import java.net.SocketTimeoutException
import javax.inject.Inject

class AzkarMasaaUseCase @Inject constructor(
    private val azkarRepository: AzkarRepository
) {

    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    suspend operator fun invoke(): AzkarMasaa {
        try {
            return azkarRepository.getAzkarMassaa()
        } catch (ex: SocketTimeoutException) {
            throw ex
        } catch (ex: NetworkException) {
            throw ex
        } catch (ex: HttpException) {
            throw ex
        } catch (ex: IOException) {
            throw ex
        } catch (ex: Exception) {
            throw ex
        }
    }
}