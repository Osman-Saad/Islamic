package com.pbws.data.repositoryimpl

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.pbws.data.constant.fromJson
import com.pbws.data.di.AhadethApiManager
import com.pbws.data.paging.AhadethPagingSource
import com.pbws.data.remote.ApiManager
import com.pbws.domain.entity.ahadethentity.Ahadeth
import com.pbws.domain.entity.ahadethentity.AhadethItem
import com.pbws.domain.repository.AhadethRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class AhadethRepositoryImpl @Inject constructor(
    @AhadethApiManager private val apiManager: ApiManager
) : AhadethRepository {
    override suspend fun getAhadeth(): Flow<PagingData<AhadethItem>> {
        try {
            return Pager(
                config = PagingConfig(pageSize = 5),
                pagingSourceFactory = {
                    AhadethPagingSource(apiManager = apiManager)
                }
            ).flow.map {pagingeData->
                pagingeData.map {
                    it.fromJson(AhadethItem::class.java)
                }
            }
        } catch (ex: Exception) {
            throw ex
        }
    }
}