package com.kayevo.bitcoinhold.data.result

sealed class AddAmountRepoResult{
    object Success: AddAmountRepoResult()
    class Error(val code: Int): AddAmountRepoResult()
    object ErrorServer: AddAmountRepoResult()
}
