package com.kayevo.bitcoinhold.network.result

sealed class CustomizeAmountRepoResult{
    object Success: CustomizeAmountRepoResult()
    class Error(val code: Int): CustomizeAmountRepoResult()
    object ErrorServer: CustomizeAmountRepoResult()
}
