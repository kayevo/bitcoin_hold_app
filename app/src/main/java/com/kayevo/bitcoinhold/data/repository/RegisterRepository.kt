package com.kayevo.bitcoinhold.data.repository

import com.kayevo.bitcoinhold.ui.result.RegisteredEmailResult
import com.kayevo.bitcoinhold.data.result.RegisterRepoResult
import com.kayevo.bitcoinhold.model.Credential

interface RegisterRepository {
    suspend fun register(credential: Credential): RegisterRepoResult
    suspend fun registeredEmail(email: String): RegisteredEmailResult
}