package com.pbws.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.pbws.data.constant.fromJson
import com.pbws.data.model.ahadeth.AhadethDto
import com.pbws.data.model.ahadeth.AhadethItemDto
import com.pbws.data.remote.ApiManager

class AhadethPagingSource(
private val apiManager: ApiManager
):PagingSource<Int, AhadethItemDto>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, AhadethItemDto> {
        val currentPage = params.key ?: 1
        return try {
            val response = apiManager.getAhadeth(page = currentPage, pageSize = 5)
            val endOfPaginationReached = response.ahadethItems?.isEmpty()

            if (response.ahadethItems?.isNotEmpty() == true) {
                LoadResult.Page(
                    data = response.ahadethItems as List<AhadethItemDto>,
                    prevKey = if (currentPage == 1) null else currentPage - 1,
                    nextKey = if (endOfPaginationReached != false) null else currentPage + 1
                )
            } else {
                LoadResult.Page(
                    data = emptyList(),
                    prevKey = null,
                    nextKey = null
                )
            }
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, AhadethItemDto>): Int? {
       return state.anchorPosition
    }

}