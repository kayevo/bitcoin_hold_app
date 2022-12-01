package com.kayevo.bitcoinhold.data.service

import com.kayevo.bitcoinhold.data.entity.AdsEntity
import com.kayevo.bitcoinhold.data.entity.IdEntity
import retrofit2.Response
import retrofit2.http.*

interface AdsService {
    @GET("ads")
    suspend fun getAllAds(
        @Header("api_key") apiKey: String
    ): Response<List<AdsEntity>>

    @GET("ads/title")
    suspend fun getAds(
        @Header("api_key") apiKey: String,
        @Query("title") title: String
    ): Response<AdsEntity>
}