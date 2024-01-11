package com.kayevo.bitcoinhold.ui.result

import com.kayevo.bitcoinhold.network.response.PortfolioResponse

sealed class PortfolioResult{
    class Success(val portfolio: PortfolioResponse): PortfolioResult()
    object ErrorServer: PortfolioResult()
}
