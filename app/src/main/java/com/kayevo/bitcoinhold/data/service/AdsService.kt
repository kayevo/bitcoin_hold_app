package com.kayevo.bitcoinhold.data.service

import com.kayevo.bitcoinhold.data.entity.AdsEntity
import com.kayevo.bitcoinhold.data.entity.IdEntity
import retrofit2.Response
import retrofit2.http.*

interface AdsService {
    @GET("ads")
    suspend fun getAllAds(
    ): Response<List<AdsEntity>>

    @GET("ads/title")
    suspend fun getAds(
        @Query("title") title: String
    ): Response<AdsEntity>

    // not implemented yet, need a admin user to access ads configuration
    /*@POST("ads")
    suspend fun setAds(
        @Query("title") title: String,
        @Query("posterUrl") posterUrl: String,
        @Query("webLink") webLink: String
    ): Response<Any>*/
}