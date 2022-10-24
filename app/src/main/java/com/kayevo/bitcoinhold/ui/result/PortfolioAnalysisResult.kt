package com.kayevo.bitcoinhold.ui.result

import com.kayevo.bitcoinhold.model.PortfolioAnalysis

sealed class PortfolioAnalysisResult{
    class Success(val portfolioAnalysis: PortfolioAnalysis): PortfolioAnalysisResult()
    object ErrorServer: PortfolioAnalysisResult()
}
