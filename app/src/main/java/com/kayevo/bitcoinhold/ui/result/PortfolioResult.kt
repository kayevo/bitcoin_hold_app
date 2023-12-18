package com.kayevo.bitcoinhold.ui.result

import com.kayevo.bitcoinhold.data.entity.PortfolioEntity

sealed class PortfolioResult{
    class Success(val portfolio: PortfolioEntity): PortfolioResult()
    object ErrorServer: PortfolioResult()
}
