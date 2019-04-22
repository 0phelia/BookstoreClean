package com.arty.presentation.di.module

import com.arty.data.BooksRepositoryImpl
import com.arty.domain.repository.BooksRepository
import dagger.Binds
import dagger.Module

@Module
abstract class DataModule {

    @Binds
    abstract fun bindDataRepository(dataRepository: BooksRepositoryImpl): BooksRepository
}

