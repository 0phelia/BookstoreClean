package com.arty.presentation.di.module

import com.arty.data.repository.BooksRemote
import com.arty.presentation.BuildConfig
import com.arty.remote.BooksRemoteImpl
import com.arty.remote.model.ApiKeyBundle
import com.arty.remote.service.GoogleBooksService
import com.arty.remote.service.NYTimesBookListFactoryServiceFactory
import com.arty.remote.service.NYTimesBookListsService
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
abstract class RemoteModule {

    @Module
    companion object {
        @Provides
        @JvmStatic
        fun provideGoogleBooksService(): GoogleBooksService {
            return NYTimesBookListFactoryServiceFactory.makeGoogleBooksService(BuildConfig.DEBUG)
        }

        @Provides
        @JvmStatic
        fun provideNytBooksService2(): NYTimesBookListsService {
            return NYTimesBookListFactoryServiceFactory.makeNYTimesBookListsService(BuildConfig.DEBUG)
        }

        @Provides
        @JvmStatic
        fun provideApiKeyBundle(): ApiKeyBundle {
            return ApiKeyBundle(BuildConfig.GOOGLE_BOOKS_API_KEY, BuildConfig.NYT_BOOKS_API_KEY)
        }

    }

    @Binds
    abstract fun bindBooksRemote(booksRemote: BooksRemoteImpl): BooksRemote
}