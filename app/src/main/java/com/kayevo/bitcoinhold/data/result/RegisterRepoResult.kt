package com.kayevo.bitcoinhold.data.result

import com.kayevo.bitcoinhold.model.User

sealed class RegisterRepoResult{
    object Success: RegisterRepoResult()
    class Error(val code: Int): RegisterRepoResult()
    object ErrorServer: RegisterRepoResult()
}
