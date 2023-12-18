package com.kayevo.bitcoinhold.data.result

sealed class RemoveAmountRepoResult{
    object Success: RemoveAmountRepoResult()
    object InsufficientAmount: RemoveAmountRepoResult()
    class Error(val code: Int): RemoveAmountRepoResult()
    object ErrorServer: RemoveAmountRepoResult()
}
