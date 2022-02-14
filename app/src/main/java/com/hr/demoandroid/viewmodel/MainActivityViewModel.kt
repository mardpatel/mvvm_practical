package com.hr.demoandroid.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.hr.demoandroid.models.BannerResponse
import com.hr.demoandroid.models.MarketListItem
import com.hr.demoandroid.network.RetroInstance
import com.hr.demoandroid.network.RetroService
import com.hr.demoandroid.paging.ProductPaging
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class MainActivityViewModel : ViewModel() {
    private var bannerListLiveData: MutableLiveData<BannerResponse> = MutableLiveData()

    fun getBannerListObserver(): MutableLiveData<BannerResponse> {
        return bannerListLiveData
    }

    fun makeApiCallBanner() {
        viewModelScope.launch(Dispatchers.IO) {
            val retroInstance = RetroInstance.getRetroInstance().create(RetroService::class.java)
            val response = retroInstance.getDataFromBannerApi("UZ")
            System.out.println("response banner: ${response}")
            bannerListLiveData.postValue(response)
        }
    }

    private var currentQueryValue: String? = null
    var currentSearchResult: Flow<PagingData<MarketListItem>>? = null

    fun searchProduct(queryString: String): Flow<PagingData<MarketListItem>> {
        currentQueryValue = queryString
        val newResult: Flow<PagingData<MarketListItem>> =
            getSearchResultStream(queryString).cachedIn(viewModelScope)
        currentSearchResult = newResult
        return newResult
    }

    private fun getSearchResultStream(query: String): Flow<PagingData<MarketListItem>> {
        return Pager(
            config = PagingConfig(
                enablePlaceholders = false,
                initialLoadSize = NETWORK_PAGE_SIZE,
                pageSize = NETWORK_PAGE_SIZE
            ),
            pagingSourceFactory = {
                ProductPaging(
                    RetroInstance.getRetroInstance().create(RetroService::class.java), query
                )
            }
        ).flow
    }

    companion object {
        private const val NETWORK_PAGE_SIZE = 30
    }
}