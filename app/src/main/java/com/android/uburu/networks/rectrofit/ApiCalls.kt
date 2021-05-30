package com.android.uburu.networks.rectrofit

import android.util.Log
import com.android.uburu.genericNetworkErrorMsg
import com.android.uburu.networks.rectrofit.dto.ConfirmOtpDto
import com.android.uburu.networks.rectrofit.dto.LoginDto
import com.android.uburu.networks.rectrofit.dto.RegisterDto
import com.google.gson.JsonObject
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import javax.inject.Inject

class ApiCalls @Inject constructor(retrofit: Retrofit, private val token: Map<String, String>) {

    private var iApi: IApi = retrofit.create(IApi::class.java)

    fun register(body: JSONObject, iResponse: IResponse<RegisterDto>)
    {
        try {
            val call: Call<RegisterDto> = iApi.register(body)
            call.enqueue(object : Callback<RegisterDto> {
                override fun onResponse(call: Call<RegisterDto>, response: Response<RegisterDto>) {
                    when {
                        response.isSuccessful -> {
                            if (response.body() != null) {
                                iResponse.onSuccess(response.body()!!)
                            } else {
                                iResponse.onFailure(response.message())
                            }
                        }

                        else -> {
                            iResponse.onFailure(response.message())
                        }
                    }
                }

                override fun onFailure(call: Call<RegisterDto>, t: Throwable) {
                    Log.e("@onFailure", t.toString());

                    if (t.localizedMessage != null)
                        iResponse.onNetworkError(t.localizedMessage!!)
                    else
                        iResponse.onNetworkError(genericNetworkErrorMsg)
                }
            })
        }catch (ex: Exception)
        {
            ex.printStackTrace()
            if (ex.message != null)
                iResponse.onFailure(ex.message!!)
            else
                iResponse.onFailure(genericNetworkErrorMsg)
        }
    }

    fun confirmOtp(body: JsonObject, iResponse: IResponse<ConfirmOtpDto>)
    {
        try {
            val call: Call<ConfirmOtpDto> = iApi.confirmOtp(body)
            call.enqueue(object : Callback<ConfirmOtpDto> {
                override fun onResponse(call: Call<ConfirmOtpDto>, response: Response<ConfirmOtpDto>) {
                    when {
                        response.isSuccessful -> {
                            if (response.body() != null) {
                                iResponse.onSuccess(response.body()!!)
                            } else {
                                iResponse.onFailure(response.message())
                            }
                        }

                        else -> {
                            iResponse.onFailure(response.message())
                        }
                    }
                }

                override fun onFailure(call: Call<ConfirmOtpDto>, t: Throwable) {
                    Log.e("@onFailure", t.toString());

                    if (t.localizedMessage != null)
                        iResponse.onNetworkError(t.localizedMessage!!)
                    else
                        iResponse.onNetworkError(genericNetworkErrorMsg)
                }
            })
        }catch (ex: Exception)
        {
            ex.printStackTrace()
            if (ex.message != null)
                iResponse.onFailure(ex.message!!)
            else
                iResponse.onFailure(genericNetworkErrorMsg)
        }
    }

    fun login(body: JsonObject, iResponse: IResponse<LoginDto>)
    {
        try {
            val call: Call<LoginDto> = iApi.login(body)
            call.enqueue(object : Callback<LoginDto> {
                override fun onResponse(call: Call<LoginDto>, response: Response<LoginDto>) {
                    when {
                        response.isSuccessful -> {
                            if (response.body() != null) {
                                iResponse.onSuccess(response.body()!!)
                            } else {
                                iResponse.onFailure(response.message())
                            }
                        }

                        else -> {
                            iResponse.onFailure(response.message())
                        }
                    }
                }

                override fun onFailure(call: Call<LoginDto>, t: Throwable) {
                    Log.e("@onFailure", t.toString());

                    if (t.localizedMessage != null)
                        iResponse.onNetworkError(t.localizedMessage!!)
                    else
                        iResponse.onNetworkError(genericNetworkErrorMsg)
                }
            })
        }catch (ex: Exception)
        {
            ex.printStackTrace()
            if (ex.message != null)
                iResponse.onFailure(ex.message!!)
            else
                iResponse.onFailure(genericNetworkErrorMsg)
        }
    }
}