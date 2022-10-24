package com.kayevo.bitcoinhold.data.service

import com.kayevo.bitcoinhold.data.entity.PortfolioEntity
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface PortfolioService {
    @GET("portfolio")
    suspend fun getPortfolio(
        @Query("userId") userId: String
    ): Response<PortfolioEntity>

    @POST("portfolio/add")
    suspend fun addFunds(
        @Query("userId") userId: String,
        @Query("satoshiAmount") satoshiAmount: Long,
        @Query("bitcoinAveragePrice") bitcoinAveragePrice: Double
    ): Response<Any>

    @POST("portfolio/customize")
    suspend fun customizeFunds(
        @Query("userId") userId: String,
        @Query("satoshiAmount") satoshiAmount: Long,
        @Query("bitcoinAveragePrice") bitcoinAveragePrice: Double
    ): Response<Any>

    @POST("portfolio/remove")
    suspend fun removeFunds(
        @Query("userId") userId: String,
        @Query("satoshiAmount") satoshiAmount: Long
    ): Response<Any>
}