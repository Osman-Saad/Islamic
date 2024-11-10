package com.pbws.data.datasourceimpl

import android.net.http.HttpException
import android.net.http.NetworkException
import android.os.Build
import androidx.annotation.RequiresExtension
import com.pbws.data.datasource.AzkarDataSource
import com.pbws.data.di.AzkarApiManager
import com.pbws.data.model.azkar.masaa.AzkarMasaa
import com.pbws.data.model.azkar.masaa.AzkarMasaaDto
import com.pbws.data.model.azkar.sabah.AzkarSabah
import com.pbws.data.model.azkar.sabah.AzkarSabahDto
import com.pbws.data.remote.ApiManager
import java.io.IOException
import java.net.SocketTimeoutException
import javax.inject.Inject

class AzkarDataSourceImpl @Inject constructor(
    @AzkarApiManager private val apiManager:ApiManager
):AzkarDataSource {
    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    override suspend fun getAzkarSabah(): AzkarSabahDto {
        try {
            return apiManager.getAzkarSabah()
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
    override suspend fun getAzkarMassaa(): AzkarMasaaDto {
        try {
            return apiManager.getAzkarMasaa()
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