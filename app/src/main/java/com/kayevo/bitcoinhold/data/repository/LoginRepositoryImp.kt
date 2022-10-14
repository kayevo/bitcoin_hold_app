package com.kayevo.bitcoinhold.data.repository

import com.kayevo.bitcoinhold.data.result.LoginRepoResult
import com.kayevo.bitcoinhold.data.service.UserService
import com.kayevo.bitcoinhold.model.User

class LoginRepositoryImp(
    private val userService: UserService
) : LoginRepository {

    override suspend fun login(email: String, password: String): LoginRepoResult {
        return try {
            val loginResponse = userService.login(email, password)

            return when (val responseCode = loginResponse.code()){
                200 ->{
                    loginResponse.body()?.let { user ->
                        LoginRepoResult.Success(User(user))
                    }?: run{
                        LoginRepoResult.ErrorServer
                    }
                }
                else ->{
                    LoginRepoResult.Error(responseCode)
                }
            }
        }catch (e: Exception){
            LoginRepoResult.ErrorServer
        }
    }
}