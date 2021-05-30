package com.android.uburu.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.uburu.networks.rectrofit.ApiCalls
import com.android.uburu.networks.rectrofit.IResponse
import com.android.uburu.networks.rectrofit.dto.LoginDto
import com.android.uburu.repository.Repository
import com.android.uburu.repository.ResponseObjectMapper
import com.google.gson.JsonObject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LoginViewModel : ViewModel()
{
    private val responseMutableLiveData: MutableLiveData<ResponseObjectMapper> = MutableLiveData()

    fun login(requestBody : JsonObject, apiCalls: ApiCalls): LiveData<ResponseObjectMapper?>
    {
        try {
            viewModelScope.launch {
                withContext(Dispatchers.IO) {
                    Thread(Login(requestBody, apiCalls, responseMutableLiveData)).start()
                }
            }
        } catch (ex: IllegalThreadStateException) {
            ex.printStackTrace()
        }

        return responseMutableLiveData
    }

    class Login(private val requestBody : JsonObject, private val apiCalls: ApiCalls, private val responseMutableLiveData: MutableLiveData<ResponseObjectMapper>): Runnable
    {
        override fun run(){
            Repository(apiCalls).login(requestBody, object : IResponse<LoginDto> {
                override fun onSuccess(res: LoginDto) {
                    responseMutableLiveData.postValue(ResponseObjectMapper (true, res))
                }

                override fun onFailure(res: String) {
                    responseMutableLiveData.postValue(ResponseObjectMapper (false, res))
                }

                override fun onNetworkError(res: String) {
                    responseMutableLiveData.postValue(ResponseObjectMapper (false, res))
                }
            })
        }
    }
}