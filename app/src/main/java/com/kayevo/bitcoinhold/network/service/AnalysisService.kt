package com.kayevo.bitcoinhold.network.service

import com.kayevo.bitcoinhold.network.response.AnalysisResponse
import retrofit2.Response
import retrofit2.http.*

interface AnalysisService {

    @GET("analysis")
    suspend fun getAnalysis(
        @Header("api_key") apiKey: String,
        @Query("userId") userId: String,
    ): Response<AnalysisResponse>
}