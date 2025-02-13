package dev.mobile.medicalink.api.rpps

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dev.mobile.medicalink.BuildConfig
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiRppsClient {
    private val BASE_URL = BuildConfig.API_RPPS_URL

    private val gson: Gson by lazy {
        GsonBuilder().setLenient().create()
    }

    private val httpClient: OkHttpClient by lazy {
        OkHttpClient.Builder().build()
    }

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(httpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    val apiService: ApiRppsService by lazy {
        retrofit.create(ApiRppsService::class.java)
    }
}