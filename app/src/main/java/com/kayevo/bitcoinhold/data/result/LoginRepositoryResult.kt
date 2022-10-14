package com.kayevo.bitcoinhold.data.result

import com.kayevo.bitcoinhold.model.User

sealed class LoginRepositoryResult{
    class Success(val user: User): LoginRepositoryResult()
    object NotFound: LoginRepositoryResult()
    class Error(val code: Int): LoginRepositoryResult()
    object ErrorServer: LoginRepositoryResult()
}
