package com.kayevo.bitcoinhold.data.repository

import com.kayevo.bitcoinhold.data.result.AnalysisRepoResult

interface AnalysisRepository {
    suspend fun getAnalysis(apiKey: String, userId: String): AnalysisRepoResult
}