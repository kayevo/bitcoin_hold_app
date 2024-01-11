package com.kayevo.bitcoinhold.network.result

sealed class RegisterRepoResult{
    object Success: RegisterRepoResult()
    class Error(val code: Int): RegisterRepoResult()
    object ErrorServer: RegisterRepoResult()
}
