package com.kayevo.bitcoinhold.data.repository

import com.kayevo.bitcoinhold.data.result.RegisterRepoResult
import com.kayevo.bitcoinhold.model.Credential
import com.kayevo.bitcoinhold.model.User

interface RegisterRepository {
    suspend fun register(credential: Credential): RegisterRepoResult
}