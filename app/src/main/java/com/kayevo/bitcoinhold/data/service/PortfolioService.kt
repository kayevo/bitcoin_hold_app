package com.kayevo.bitcoinhold.data.service

import com.kayevo.bitcoinhold.data.entity.IdEntity
import com.kayevo.bitcoinhold.data.entity.PortfolioEntity
import retrofit2.Response
import retrofit2.http.*

interface PortfolioService {

    @GET("portfolio")
    suspend fun getPortfolio(
        @Query("userId") userId: String
    ): Response<PortfolioEntity>
}