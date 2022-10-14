package com.kayevo.bitcoinhold.data.result

import com.kayevo.bitcoinhold.model.User

sealed class LoginRepoResult{
    class Success(val user: User): LoginRepoResult()
    object NotFound: LoginRepoResult()
    class Error(val code: Int): LoginRepoResult()
    object ErrorServer: LoginRepoResult()
}
