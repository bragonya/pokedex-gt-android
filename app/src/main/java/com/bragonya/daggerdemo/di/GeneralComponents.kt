package com.bragonya.daggerdemo.di

import com.bragonya.daggerdemo.network.PokeAPI
import com.bragonya.daggerdemo.repositories.PokeRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@Module
@InstallIn(ApplicationComponent::class)
class GeneralComponents {

    @Provides
    fun repositoryProvider(pokeAPI: PokeAPI) = PokeRepository(pokeAPI)
}