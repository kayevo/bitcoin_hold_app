package com.kayevo.bitcoinhold.network.result

sealed class RemoveAmountRepoResult{
    object Success: RemoveAmountRepoResult()
    object InsufficientAmount: RemoveAmountRepoResult()
    class Error(val code: Int): RemoveAmountRepoResult()
    object ErrorServer: RemoveAmountRepoResult()
}
