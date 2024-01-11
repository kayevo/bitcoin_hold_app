package com.kayevo.bitcoinhold.network.result

import com.kayevo.bitcoinhold.network.response.PortfolioResponse

sealed class PortfolioRepoResult{
    class Success(val portfolio: PortfolioResponse): PortfolioRepoResult()
    object NotFound: PortfolioRepoResult()
    class Error(val code: Int): PortfolioRepoResult()
    object ErrorServer: PortfolioRepoResult()
}
