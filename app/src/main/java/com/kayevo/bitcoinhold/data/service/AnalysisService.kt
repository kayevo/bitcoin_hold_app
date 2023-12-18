package com.kayevo.bitcoinhold.data.service

import com.kayevo.bitcoinhold.data.entity.AnalysisEntity
import retrofit2.Response
import retrofit2.http.*

interface AnalysisService {

    @GET("analysis")
    suspend fun getAnalysis(
        @Header("api_key") apiKey: String,
        @Query("userId") userId: String,
    ): Response<AnalysisEntity>
}