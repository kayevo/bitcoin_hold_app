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

    @POST("portfolio/customize")
    suspend fun setPortfolio(
        @Header("api_key") apiKey: String,
        @Query("userId") userId: String,
        @Query("amount") amount: Long,
        @Query("totalPaidValue") totalPaidValue: Double
    ): Response<Any>

    @POST("portfolio/add")
    suspend fun addAmount(
        @Header("api_key") apiKey: String,
        @Query("userId") userId: String,
        @Query("amount") amount: Long,
        @Query("paidValue") paidValue: Double
    ): Response<Any>

    @POST("portfolio/remove")
    suspend fun removeAmount(
        @Header("api_key") apiKey: String,
        @Query("userId") userId: String,
        @Query("amount") amount: Long,
    ): Response<Any>
}