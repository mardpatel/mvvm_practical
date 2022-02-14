package com.hr.demoandroid.paging

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.hr.demoandroid.models.MarketListItem
import com.hr.demoandroid.network.RetroService

private const val UNSPLASH_STARTING_PAGE_INDEX = 1

class ProductPaging(
    private val service: RetroService,
    private val query: String
) : PagingSource<Int, MarketListItem>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MarketListItem> {
        val pageNo = params.key ?: UNSPLASH_STARTING_PAGE_INDEX
        return try {
            val response  = service.getDataFromProductApi(pageNo,13,"UZ")

            val products = response.Data?.marketList ?: arrayListOf()
            LoadResult.Page(
                data = products,
                prevKey = if (pageNo == UNSPLASH_STARTING_PAGE_INDEX) null else pageNo - 1,
                nextKey = if (params.loadSize > products.size) null else pageNo + 1
            )
        } catch (exception: Exception) {
            Log.e("exception",exception.localizedMessage)
            LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, MarketListItem>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            // prefetchDistance.
            state.closestPageToPosition(anchorPosition)?.prevKey
        }
    }
}