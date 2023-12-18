package com.kayevo.bitcoinhold.data.result

import com.kayevo.bitcoinhold.data.entity.AnalysisEntity

sealed class AnalysisRepoResult{
    class Success(val analysisEntity: AnalysisEntity): AnalysisRepoResult()
    class Error(val code: Int): AnalysisRepoResult()
    object ErrorServer: AnalysisRepoResult()
}
