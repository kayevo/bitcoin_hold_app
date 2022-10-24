package com.kayevo.bitcoinhold.data.result

sealed class CustomizeFundsRepoResult{
    object Success: CustomizeFundsRepoResult()
    class Error(val code: Int): CustomizeFundsRepoResult()
    object ErrorServer: CustomizeFundsRepoResult()
}
