package com.ahmrh.patypet.data.remote.retrofit

import android.util.Log
import androidx.camera.core.ImageProcessor
import com.ahmrh.patypet.BuildConfig
import com.ahmrh.patypet.data.local.AppPreferences
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiConfig(private val pref: AppPreferences) {

    fun getApiService(): ApiService {

        val loggingInterceptor = if(BuildConfig.DEBUG) {
            HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY ; HttpLoggingInterceptor.Level.HEADERS
            }
        } else {
            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.NONE)
        }

        val client = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()

        val apiUrl = BuildConfig.API_URL

        val retrofit = Retrofit.Builder()
            .baseUrl(apiUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()

        return retrofit.create(ApiService::class.java)
    }


    companion object{
        const val TAG = "ApiConfig"
    }
}