package com.kayevo.bitcoinhold.data.result

sealed class RegisterRepoResult{
    object Success: RegisterRepoResult()
    class Error(val code: Int): RegisterRepoResult()
    object ErrorServer: RegisterRepoResult()
}
