package com.kayevo.bitcoinhold.data.service

import com.kayevo.bitcoinhold.data.entity.PortfolioEntity
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Query

interface PortfolioService {
    @GET("portfolio")
    suspend fun getPortfolio(
        @Header("api_key") apiKey: String,
        @Query("userId") userId: String
    ): Response<PortfolioEntity>

    @POST("portfolio/add")
    suspend fun addFunds(
        @Header("api_key") apiKey: String,
        @Query("userId") userId: String,
        @Query("satoshiAmount") satoshiAmount: Long,
        @Query("bitcoinAveragePrice") bitcoinAveragePrice: Double
    ): Response<Any>

    @POST("portfolio/customize")
    suspend fun customizeFunds(
        @Header("api_key") apiKey: String,
        @Query("userId") userId: String,
        @Query("satoshiAmount") satoshiAmount: Long,
        @Query("bitcoinAveragePrice") bitcoinAveragePrice: Double
    ): Response<Any>

    @POST("portfolio/remove")
    suspend fun removeFunds(
        @Header("api_key") apiKey: String,
        @Query("userId") userId: String,
        @Query("satoshiAmount") satoshiAmount: Long
    ): Response<Any>
}