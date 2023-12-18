package com.kayevo.bitcoinhold.ui.result

import com.kayevo.bitcoinhold.data.entity.AnalysisEntity

sealed class AnalysisResult{
    class Success(val analysis: AnalysisEntity): AnalysisResult()
    object ErrorServer: AnalysisResult()
}
