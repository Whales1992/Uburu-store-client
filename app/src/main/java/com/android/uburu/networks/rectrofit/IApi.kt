package com.android.uburu.networks.rectrofit

import com.android.uburu.networks.rectrofit.dto.ConfirmOtpDto
import com.android.uburu.networks.rectrofit.dto.LoginDto
import com.android.uburu.networks.rectrofit.dto.RegisterDto
import com.google.gson.JsonObject
import org.json.JSONObject
import retrofit2.Call
import retrofit2.http.*

interface IApi {
    @POST("register")
    fun register(@Body body: JSONObject): Call<RegisterDto>

    @POST("activate")
    fun confirmOtp(@Body body : JsonObject): Call<ConfirmOtpDto>

    @POST("login")
    fun login(@Body body : JsonObject): Call<LoginDto>
}