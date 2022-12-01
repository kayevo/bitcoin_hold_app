package com.kayevo.bitcoinhold.data.repository

import com.kayevo.bitcoinhold.data.result.LoginRepoResult
import com.kayevo.bitcoinhold.model.Credential

interface LoginRepository {
    suspend fun login(apiKey: String, credentials: Credential): LoginRepoResult
}