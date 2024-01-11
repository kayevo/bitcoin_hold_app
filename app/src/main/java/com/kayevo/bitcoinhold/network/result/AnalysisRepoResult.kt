package com.kayevo.bitcoinhold.network.result

import com.kayevo.bitcoinhold.network.response.AnalysisResponse

sealed class AnalysisRepoResult{
    class Success(val analysisEntity: AnalysisResponse): AnalysisRepoResult()
    class Error(val code: Int): AnalysisRepoResult()
    object ErrorServer: AnalysisRepoResult()
}
