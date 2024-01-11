package com.kayevo.bitcoinhold.ui.result

import com.kayevo.bitcoinhold.network.result.LoginRepoResult

sealed class RegisteredEmailResult{
    object NotRegisteredEmail: RegisteredEmailResult()
    object RegisteredEmail: RegisteredEmailResult()
    class Error(val code: Int): RegisteredEmailResult()
    object ErrorServer: RegisteredEmailResult()
}
