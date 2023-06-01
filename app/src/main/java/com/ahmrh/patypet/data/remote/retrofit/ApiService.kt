package com.ahmrh.patypet.data.remote.retrofit

import com.ahmrh.patypet.data.remote.responses.LoginResponse
import com.ahmrh.patypet.data.remote.responses.PredictionResponse
import com.ahmrh.patypet.data.remote.responses.RegisterResponse
import com.ahmrh.patypet.data.remote.responses.UserResponse
import com.google.gson.JsonObject
import okhttp3.RequestBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST

interface ApiService {
    @Headers("Content-Type: application/json")
    @POST("register")
    fun register(
        @Body rawJsonObject: JsonObject,
    ): Call<RegisterResponse>

    @Headers("Content-Type: application/json")
    @POST("login")
    fun login(
        @Body rawJsonObject: JsonObject,
    ): Call<LoginResponse>

    @FormUrlEncoded
    @POST("predict")
    fun predict(
        @Field("email") email: String,
        @Field("password") password: String,
    ): Call<PredictionResponse>

    @FormUrlEncoded
    @POST("user")
    fun getUser(
        @Header("Authorization") token: String,
    ): Call<UserResponse>

}