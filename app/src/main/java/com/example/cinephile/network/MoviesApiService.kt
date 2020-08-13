package com.example.cinephile.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

private const val BASE_URL = "https://api.themoviedb.org/3/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

fun myHttpClient(): OkHttpClient{
    val builder = OkHttpClient().newBuilder()
        .connectTimeout(60, TimeUnit.SECONDS)
        .writeTimeout(120,TimeUnit.SECONDS)
        .readTimeout(60,TimeUnit.SECONDS)

    return builder.build()
}

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .baseUrl(BASE_URL)
    .client(myHttpClient())
    .build()

object MovieApi{
    val retrofitService: MovieApiInterface by lazy { retrofit.create(MovieApiInterface::class.java) }
}