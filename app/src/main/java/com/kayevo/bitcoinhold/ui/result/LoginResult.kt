package com.kayevo.bitcoinhold.ui.result

import com.kayevo.bitcoinhold.model.User

sealed class LoginResult{
    class Success(val user: User): LoginResult()
    object NotFound: LoginResult()
    object Error: LoginResult()
}
