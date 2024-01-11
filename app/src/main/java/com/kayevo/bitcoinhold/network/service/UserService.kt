package com.kayevo.bitcoinhold.network.service

import com.kayevo.bitcoinhold.network.response.UserResponse
import retrofit2.Response
import retrofit2.http.*

interface UserService {

    @GET("user/auth")
    suspend fun login(
        @Header("api_key") apiKey: String,
        @Query("email") email: String,
        @Query("password") password: String
    ): Response<UserResponse>

    @GET("user/email")
    suspend fun registeredEmail(
        @Header("api_key") apiKey: String,
        @Query("email") email: String
    ): Response<Any>

    @POST("user")
    suspend fun register(
        @Header("api_key") apiKey: String,
        @Query("email") email: String,
        @Query("password") password: String
    ): Response<Any>
}