package com.arty.presentation.di

import android.app.Application
import com.arty.presentation.App
import com.arty.presentation.di.module.*
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [AndroidInjectionModule::class,
        CacheModule::class,
        DataModule::class,
        PresentationModule::class,
        RemoteModule::class,
        UiModule::class,
        AppModule::class
    ])
interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    fun inject(app: App)
}