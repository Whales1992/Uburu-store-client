package com.android.uburu.di

import android.app.Application
import com.android.uburu.ApplicationClass
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule

@AppScope
@Component(modules = [
    (CoreModule::class),
    (AndroidSupportInjectionModule::class),
    (ActivityBuildersModule::class)])
interface ApplicationComponent : AndroidInjector<ApplicationClass> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): ApplicationComponent?
    }

}