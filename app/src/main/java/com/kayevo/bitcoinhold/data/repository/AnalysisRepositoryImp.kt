package com.kayevo.bitcoinhold.data.repository

import android.util.Log
import com.kayevo.bitcoinhold.data.helper.HttpStatusCodeHelper
import com.kayevo.bitcoinhold.data.result.AnalysisRepoResult
import com.kayevo.bitcoinhold.data.service.AnalysisService

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