package com.kayevo.bitcoinhold.data.service

import com.kayevo.bitcoinhold.data.entity.IdEntity
import retrofit2.Response
import retrofit2.http.*

interface UserService {

    @GET("user/auth")
    suspend fun login(
        @Header("api_key") apiKey: String,
        @Query("email") email: String,
        @Query("password") password: String
    ): Response<IdEntity>

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