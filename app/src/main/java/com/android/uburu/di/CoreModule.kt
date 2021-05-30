package com.android.uburu.di

import android.app.Application
import com.android.uburu.ApiToken
import com.android.uburu.ImageBaseUrl
import com.android.uburu.RequestBaseUrl
import com.squareup.picasso.OkHttp3Downloader
import com.squareup.picasso.Picasso
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.security.SecureRandom
import java.security.cert.X509Certificate
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.net.ssl.SSLContext
import javax.net.ssl.SSLSocketFactory
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager

@Module
class CoreModule {

    @AppScope
    @Provides
    @Named("token")
    fun provideToken(): Map<String, String>{
        return mapOf("api_key" to ApiToken)
    }

    @AppScope
    @Provides
    fun provideTrustManager(): Array<TrustManager> {
        return arrayOf<TrustManager>( object : X509TrustManager {
            override fun checkClientTrusted(
                chain: Array<X509Certificate>,
                authType: String
            ) {
                //Todo ...
            }

            override fun checkServerTrusted(
                chain: Array<X509Certificate>,
                authType: String
            ) {
                //Todo ...
            }

            override fun getAcceptedIssuers(): Array<X509Certificate> {
                return arrayOf()
            }
        } )
    }

    @AppScope
    @Provides
    fun provideRandomCertificate(provideTrustManager :Array<TrustManager>): SSLSocketFactory{
        val trustAllSslContext = SSLContext.getInstance("SSL")
        trustAllSslContext.init(
            null,
            provideTrustManager,
            SecureRandom()
        )

        return trustAllSslContext.socketFactory
    }

    @AppScope
    @Provides
    fun provideClient(provideTrustManager :Array<TrustManager>) : OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        return OkHttpClient.Builder()
                .addInterceptor(Interceptor { chain ->
                    val ongoing = chain.request().newBuilder()
                    chain.proceed(ongoing.build())
                })
                .sslSocketFactory(provideRandomCertificate(provideTrustManager), provideTrustManager[0] as X509TrustManager) //comment out when going live
                .readTimeout(30, TimeUnit.SECONDS)
                .connectTimeout(30, TimeUnit.SECONDS)
                .addInterceptor(interceptor)
                .build()
    }

    @AppScope
    @Provides
    fun provideNetworkClient(provideClient : OkHttpClient) : Retrofit {
        return Retrofit.Builder()
            .baseUrl(RequestBaseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(provideClient)
            .build()
    }

    @AppScope
    @Provides
    fun okHttp3Downloader(provideClient : OkHttpClient):OkHttp3Downloader {
        return OkHttp3Downloader(provideClient)
    }

    @Provides
    @AppScope
    fun picasso(application: Application, okHttp3Downloader: OkHttp3Downloader): Picasso {
        return Picasso.Builder(application)
                .downloader(okHttp3Downloader)
                .loggingEnabled(true)
                .build()
    }

    @Provides
    @AppScope
    @Named("imageBaseUrl200")
    fun imageBaseUrl200():String{
        return "${ImageBaseUrl}w200"
    }

    @Provides
    @AppScope
    @Named("imageBaseUrl400")
    fun imageBaseUrl500():String{
        return "${ImageBaseUrl}w400"
    }
}
