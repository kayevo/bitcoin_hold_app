package com.kayevo.bitcoinhold.data.result

import com.kayevo.bitcoinhold.data.entity.PortfolioEntity

sealed class PortfolioRepoResult{
    class Success(val portfolio: PortfolioEntity): PortfolioRepoResult()
    object NotFound: PortfolioRepoResult()
    class Error(val code: Int): PortfolioRepoResult()
    object ErrorServer: PortfolioRepoResult()
}
