package com.kayevo.bitcoinhold.data.result

sealed class AddFundsRepoResult{
    object Success: AddFundsRepoResult()
    class Error(val code: Int): AddFundsRepoResult()
    object ErrorServer: AddFundsRepoResult()
}
