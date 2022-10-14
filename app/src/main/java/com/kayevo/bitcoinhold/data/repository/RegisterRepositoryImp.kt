package com.kayevo.bitcoinhold.data.repository

import com.kayevo.bitcoinhold.data.result.LoginRepoResult
import com.kayevo.bitcoinhold.data.result.RegisterRepoResult
import com.kayevo.bitcoinhold.data.service.UserService
import com.kayevo.bitcoinhold.model.Credential

class RegisterRepositoryImp(
    private val userService: UserService
) : RegisterRepository {

    override suspend fun register(credential: Credential): RegisterRepoResult {
        return try {
            val registerResponse = userService.register(credential.email, credential.password)

            return when (val responseCode = registerResponse.code()){
                200 ->{
                    registerResponse.body()?.let { isAccountRegistered ->
                        if (isAccountRegistered) RegisterRepoResult.Success
                        else RegisterRepoResult.ErrorServer

                    }?: run{
                        RegisterRepoResult.ErrorServer
                    }
                }
                else ->{
                    RegisterRepoResult.Error(responseCode)
                }
            }
        }catch (e: Exception){
            RegisterRepoResult.ErrorServer
        }
    }
}