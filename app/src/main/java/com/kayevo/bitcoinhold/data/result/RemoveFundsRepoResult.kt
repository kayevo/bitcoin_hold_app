package com.kayevo.bitcoinhold.data.result

sealed class RemoveFundsRepoResult{
    object Success: RemoveFundsRepoResult()
    object InsufficientFunds: RemoveFundsRepoResult()
    class Error(val code: Int): RemoveFundsRepoResult()
    object ErrorServer: RemoveFundsRepoResult()
}
