package com.kayevo.bitcoinhold.ui.result

import com.kayevo.bitcoinhold.data.entity.PortfolioEntity
import com.kayevo.bitcoinhold.model.Portfolio

sealed class PortfolioResult{
    class Success(val portfolio: Portfolio): PortfolioResult()
    object ErrorServer: PortfolioResult()
}
