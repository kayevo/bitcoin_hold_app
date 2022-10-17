package com.kayevo.bitcoinhold.data.result

sealed class LoginRepoResult{
    class Success(val userId: String): LoginRepoResult()
    object NotFound: LoginRepoResult()
    class Error(val code: Int): LoginRepoResult()
    object ErrorServer: LoginRepoResult()
}
