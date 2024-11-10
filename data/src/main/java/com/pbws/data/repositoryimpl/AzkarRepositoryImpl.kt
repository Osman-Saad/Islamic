package com.pbws.data.repositoryimpl

import android.net.http.HttpException
import android.net.http.NetworkException
import android.os.Build
import androidx.annotation.RequiresExtension
import com.pbws.data.constant.fromJson
import com.pbws.data.datasource.AzkarDataSource
import com.pbws.data.model.azkar.masaa.AzkarMasaa
import com.pbws.data.model.azkar.sabah.AzkarSabah
import com.pbws.domain.repository.AzkarRepository
import java.io.IOException
import java.net.SocketTimeoutException
import javax.inject.Inject

class AzkarRepositoryImpl @Inject constructor(
    private val azkarDataSource: AzkarDataSource
):AzkarRepository {
    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    override suspend fun getAzkarSabah(): AzkarSabah {
        try {
            return azkarDataSource.getAzkarSabah().fromJson(AzkarSabah::class.java)
        }catch (ex:SocketTimeoutException){
            throw ex
        }catch (ex:NetworkException){
            throw ex
        }catch (ex:HttpException){
            throw ex
        }catch (ex:IOException){
            throw ex
        }
        catch (ex:Exception){
            throw ex
        }
    }

    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    override suspend fun getAzkarMassaa(): AzkarMasaa {
        try {
            return azkarDataSource.getAzkarMassaa().fromJson(AzkarMasaa::class.java)
        }catch (ex:SocketTimeoutException){
            throw ex
        }catch (ex:NetworkException){
            throw ex
        }catch (ex:HttpException){
            throw ex
        }catch (ex:IOException){
            throw ex
        }
        catch (ex:Exception){
            throw ex
        }
    }

}