package com.kayevo.bitcoinhold.ui.result

import com.kayevo.bitcoinhold.network.response.AnalysisResponse

sealed class AnalysisResult{
    class Success(val analysis: AnalysisResponse): AnalysisResult()
    object ErrorServer: AnalysisResult()
}
