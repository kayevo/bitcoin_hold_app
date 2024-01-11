package com.kayevo.bitcoinhold.network.repository

import com.kayevo.bitcoinhold.network.result.AnalysisRepoResult

interface AnalysisRepository {
    suspend fun getAnalysis(apiKey: String, userId: String): AnalysisRepoResult
}