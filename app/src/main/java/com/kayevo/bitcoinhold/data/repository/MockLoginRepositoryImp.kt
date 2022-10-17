package com.kayevo.bitcoinhold.data.repository

import com.kayevo.bitcoinhold.data.entity.UserEntity
import com.kayevo.bitcoinhold.data.result.LoginRepoResult
import com.kayevo.bitcoinhold.data.service.UserService
import com.kayevo.bitcoinhold.model.Credential

class MockLoginRepositoryImp(
    private val userService: UserService
): LoginRepository {
    override suspend fun login(credentials: Credential): LoginRepoResult {
        return LoginRepoResult.Success("userId")
    }
}