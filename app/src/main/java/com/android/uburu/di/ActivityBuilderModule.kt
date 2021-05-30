package com.android.uburu.di

import com.android.uburu.ui.ConfirmOtpActivity
import com.android.uburu.ui.LoginActivity
import com.android.uburu.ui.RegisterActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuildersModule {

    @ContributesAndroidInjector
    abstract fun contributeLoginActivity(): LoginActivity?

    @ContributesAndroidInjector
    abstract fun contributeRegisterActivity(): RegisterActivity?

    @ContributesAndroidInjector
    abstract fun contributeConfirmOtpActivity(): ConfirmOtpActivity?

}