package com.kayevo.bitcoinhold.network.service

import com.kayevo.bitcoinhold.network.response.AdsResponse
import retrofit2.Response
import retrofit2.http.*

interface AdsService {
    @GET("ads")
    suspend fun getAllAds(
        @Header("api_key") apiKey: String
    ): Response<List<AdsResponse>>

    @GET("ads/title")
    suspend fun getAds(
        @Header("api_key") apiKey: String,
        @Query("title") title: String
    ): Response<AdsResponse>
}