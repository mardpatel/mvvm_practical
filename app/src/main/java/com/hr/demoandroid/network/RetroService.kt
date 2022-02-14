package com.hr.demoandroid.network

import com.hr.demoandroid.models.BannerResponse
import com.hr.demoandroid.models.ProductResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface RetroService {

    @GET("productlist")
    suspend fun getDataFromProductApi(@Query("page") page : Int,@Query("productTagId") productTagId : Int,@Query("marketCode") marketCode : String): ProductResponse

    @GET("home")
    suspend fun getDataFromBannerApi(@Query("marketCode") marketCode : String): BannerResponse
}