package com.example.androidcoroutinesretrofit

import android.util.Log
import com.example.androidcoroutinesretrofit.model.Users
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

const val BASE_URL = "https://randomuser.me/"
interface UserApiService {
    @GET("api")
    suspend fun getUsers(@Query("results") results: Int): Response<Users>

    companion object{
        fun create():UserApiService{
            val logger = HttpLoggingInterceptor{ Log.d("mars", it)}
            logger.level = HttpLoggingInterceptor.Level.BASIC
            val client = OkHttpClient.Builder().addInterceptor(logger).build()
            return Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).client(client).build().create(UserApiService::class.java)

        }
    }
}