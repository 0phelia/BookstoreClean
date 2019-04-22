package com.arty.presentation.di.module

import com.arty.presentation.browse.BrowseFragment
import com.arty.presentation.detail.DetailFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class UiModule {

    @ContributesAndroidInjector
    abstract fun contibuteBrowseFragment(): BrowseFragment

    @ContributesAndroidInjector
    abstract fun contibuteDetailsFragment(): DetailFragment

}