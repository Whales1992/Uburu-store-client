package com.android.uburu.ui

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.android.uburu.databinding.ActivityRegisterBinding
import com.android.uburu.networks.rectrofit.ApiCalls
import com.android.uburu.networks.rectrofit.dto.RegisterDto
import com.android.uburu.viewmodel.RegisterViewModel
import com.android.uburu.viewmodel.ViewModelFactory
import com.squareup.picasso.Picasso
import dagger.android.support.DaggerAppCompatActivity
import org.json.JSONObject
import retrofit2.Retrofit
import javax.inject.Inject
import javax.inject.Named

class RegisterActivity : DaggerAppCompatActivity(){
    private lateinit var binding: ActivityRegisterBinding

    private lateinit var registerViewModel : RegisterViewModel

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
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        registerViewModel = ViewModelProvider(this, ViewModelFactory()).get(RegisterViewModel::class.java)

        binding.signUp.setOnClickListener{
            val addressBody = JSONObject()
            addressBody.put("street", "Labora Street")
            addressBody.put("state", "Lagos")
            addressBody.put("lga", "Eti-Osa")

            val requestBody = JSONObject()
            requestBody.put("fullNames", "${binding.lastName.text.toString()} ${binding.firstName.text.toString()}")
            requestBody.put("phone", "09057458888")
            requestBody.put("email", binding.username.text.toString())
            requestBody.put("password", binding.password.text.toString())
            requestBody.put("username", "walewalewale")
            requestBody.put("address", addressBody)

            registerViewModel.register(requestBody, ApiCalls(provideNetworkClient, token)).observe(this, { t ->
                if (t != null && t.isSuccess()) {
                    runOnUiThread {
                        updateView(t.responseObject() as RegisterDto);
                    }
                }
            })
        }
    }

    @Suppress("UNCHECKED_CAST")
    private fun updateView(registerDto: RegisterDto) {

    }

}