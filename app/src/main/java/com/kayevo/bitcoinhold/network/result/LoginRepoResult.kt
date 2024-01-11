package com.kayevo.bitcoinhold.network.result

sealed class LoginRepoResult{
    class Success(val userId: String): LoginRepoResult()
    object NotFound: LoginRepoResult()
    class Error(val code: Int): LoginRepoResult()
    object ErrorServer: LoginRepoResult()
}
