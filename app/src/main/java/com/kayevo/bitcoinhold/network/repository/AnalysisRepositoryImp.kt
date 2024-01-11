package com.kayevo.bitcoinhold.network.repository

import android.util.Log
import com.kayevo.bitcoinhold.helper.HttpStatusCodeHelper
import com.kayevo.bitcoinhold.network.result.AnalysisRepoResult
import com.kayevo.bitcoinhold.network.service.AnalysisService

class AnalysisRepositoryImp(
    private val analysisService: AnalysisService
) : AnalysisRepository {

    override suspend fun getAnalysis(apiKey: String, userId: String): AnalysisRepoResult {
        return try {
            val analysisResponse = analysisService.getAnalysis(
                apiKey = apiKey,
                userId = userId
            )

            return when (val responseCode = analysisResponse.code()){
                HttpStatusCodeHelper.OK.code ->{
                    analysisResponse.body()?.let { analysisEntity ->
                        AnalysisRepoResult.Success(analysisEntity)
                    }?: run{
                        AnalysisRepoResult.ErrorServer
                    }
                }
                else ->{
                    AnalysisRepoResult.Error(responseCode)
                }
            }
        }catch (e: Exception){
            Log.d("Error kayevo:", e.toString())
            AnalysisRepoResult.ErrorServer
        }
    }
}