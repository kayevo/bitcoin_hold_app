package com.kayevo.bitcoinhold.data.repository

import com.kayevo.bitcoinhold.data.result.LoginRepositoryResult
import com.kayevo.bitcoinhold.data.service.LoginService
import com.kayevo.bitcoinhold.model.User

class LoginRepositoryImp(
    private val loginService: LoginService
) : LoginRepository {

    override suspend fun login(email: String, password: String): LoginRepositoryResult {
        return try {
            val loginResponse = loginService.login(email, password)

            return when (val responseCode = loginResponse.code()){
                200 ->{
                    loginResponse.body()?.let { user ->
                        LoginRepositoryResult.Success(user)
                    }?: run{
                        LoginRepositoryResult.ErrorServer
                    }
                }
                else ->{
                    LoginRepositoryResult.Error(responseCode)
                }
            }
        }catch (e: Exception){
            LoginRepositoryResult.ErrorServer
        }
    }
}