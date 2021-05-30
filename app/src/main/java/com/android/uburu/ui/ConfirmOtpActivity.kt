package com.android.uburu.ui

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.android.uburu.R
import com.android.uburu.databinding.ActivityConfirmOtpBinding
import com.android.uburu.networks.rectrofit.ApiCalls
import com.android.uburu.networks.rectrofit.dto.ConfirmOtpDto
import com.android.uburu.viewmodel.ConfirmOtpViewModel
import com.android.uburu.viewmodel.ViewModelFactory
import com.google.gson.JsonObject
import com.squareup.picasso.Picasso
import dagger.android.support.DaggerAppCompatActivity
import retrofit2.Retrofit
import javax.inject.Inject
import javax.inject.Named

class ConfirmOtpActivity : DaggerAppCompatActivity(){
    private lateinit var binding: ActivityConfirmOtpBinding

    private lateinit var confirmOtpViewModel: ConfirmOtpViewModel

    @Inject
    lateinit var provideNetworkClient: Retrofit

    @Inject
    lateinit var picasso: Picasso

    @Inject
    @Named("token")
    lateinit var token: Map<String, String>

    @Inject
    @Named("imageBaseUrl200")
    lateinit var imageBaseUrl200: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityConfirmOtpBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val requestBody = JsonObject()

        confirmOtpViewModel = ViewModelProvider(this, ViewModelFactory()).get(ConfirmOtpViewModel::class.java)
        confirmOtpViewModel.confirmOtp(requestBody, ApiCalls(provideNetworkClient, token)).observe(this, { t ->
            if (t != null && t.isSuccess()) {
                runOnUiThread {
                    updateView(t.responseObject() as ConfirmOtpDto);
                }
            }
        })
    }

    @Suppress("UNCHECKED_CAST")
    private fun updateView(confirmOtpDto: ConfirmOtpDto) {
    }

}