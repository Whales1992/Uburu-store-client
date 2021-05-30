package com.android.uburu.repository

import com.android.uburu.networks.rectrofit.ApiCalls
import com.android.uburu.networks.rectrofit.IResponse
import com.android.uburu.networks.rectrofit.dto.ConfirmOtpDto
import com.android.uburu.networks.rectrofit.dto.LoginDto
import com.android.uburu.networks.rectrofit.dto.RegisterDto
import com.google.gson.JsonObject
import org.json.JSONObject

class Repository(private val apiCalls: ApiCalls) {
    fun register(body: JSONObject, iResponse: IResponse<RegisterDto>) {
        apiCalls.register(body, iResponse)
    }

    fun confirmOtp(body : JsonObject, iResponse: IResponse<ConfirmOtpDto>) {
        apiCalls.confirmOtp(body, iResponse)
    }

    fun login(body : JsonObject, iResponse: IResponse<LoginDto>) {
        apiCalls.login(body, iResponse)
    }
}