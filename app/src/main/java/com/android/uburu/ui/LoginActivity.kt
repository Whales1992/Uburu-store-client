package com.android.uburu.ui

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.android.uburu.R
import com.android.uburu.databinding.ActivityLoginBinding
import com.android.uburu.networks.rectrofit.ApiCalls
import com.android.uburu.networks.rectrofit.dto.LoginDto
import com.android.uburu.viewmodel.LoginViewModel
import com.android.uburu.viewmodel.ViewModelFactory
import com.google.gson.JsonObject
import com.squareup.picasso.Picasso
import dagger.android.support.DaggerAppCompatActivity
import retrofit2.Retrofit
import javax.inject.Inject
import javax.inject.Named

class LoginActivity : DaggerAppCompatActivity(){
    private lateinit var binding: ActivityLoginBinding

    private lateinit var loginViewModel : LoginViewModel

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
        binding = ActivityLoginBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        loginViewModel = ViewModelProvider(this, ViewModelFactory()).get(LoginViewModel::class.java)

        binding.login.setOnClickListener{

            val requestBody = JsonObject()
            requestBody.addProperty("email", binding.username.text.toString())
            requestBody.addProperty("password", binding.password.text.toString())

            loginViewModel.login(requestBody, ApiCalls(provideNetworkClient, token)).observe(this, { t ->
                if (t != null && t.isSuccess()) {
                    runOnUiThread {
                        updateView(t.responseObject() as LoginDto);
                    }
                }
            })

        }

        binding.gotoSignUp.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }

    @Suppress("UNCHECKED_CAST")
    private fun updateView(loginDto: LoginDto) {
    }

}