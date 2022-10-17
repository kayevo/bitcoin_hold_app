package com.kayevo.bitcoinhold.data.repository

import com.kayevo.bitcoinhold.data.result.RegisterRepoResult
import com.kayevo.bitcoinhold.data.service.UserService
import com.kayevo.bitcoinhold.model.Credential
import com.kayevo.bitcoinhold.ui.result.RegisteredEmailResult

class MockRegisterRepositoryImp(
    private val userService: UserService
) : RegisterRepository {
    override suspend fun register(credential: Credential): RegisterRepoResult {
        return RegisterRepoResult.Success
    }

    override suspend fun registeredEmail(email: String): RegisteredEmailResult {
        return RegisteredEmailResult.NotRegisteredEmail
    }

}