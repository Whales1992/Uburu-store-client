package com.android.uburu.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.uburu.networks.rectrofit.ApiCalls
import com.android.uburu.networks.rectrofit.IResponse
import com.android.uburu.networks.rectrofit.dto.RegisterDto
import com.android.uburu.repository.Repository
import com.android.uburu.repository.ResponseObjectMapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONObject

class RegisterViewModel : ViewModel()
{
    private val responseMutableLiveData: MutableLiveData<ResponseObjectMapper> = MutableLiveData()

    fun register(requestBody: JSONObject, apiCalls: ApiCalls): LiveData<ResponseObjectMapper?>
    {
        try {
            viewModelScope.launch {
                withContext(Dispatchers.IO) {
                    Thread(Register(requestBody, apiCalls, responseMutableLiveData)).start()
                }
            }
        } catch (ex: IllegalThreadStateException) {
            ex.printStackTrace()
        }

        return responseMutableLiveData
    }

    class Register(private val requestBody: JSONObject, private val apiCalls: ApiCalls, private val responseMutableLiveData: MutableLiveData<ResponseObjectMapper>): Runnable
    {
        override fun run(){
            Repository(apiCalls).register(requestBody, object : IResponse<RegisterDto> {
                override fun onSuccess(res: RegisterDto) {
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