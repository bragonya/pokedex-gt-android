package com.bragonya.daggerdemo.ui.detail

import com.bragonya.daggerdemo.repositories.PokeRepository
import com.bragonya.daggerdemo.ui.mainlist.MainViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

@Module
@InstallIn(ActivityRetainedComponent::class)
class PokemonDetailModule {

    @Provides
    fun mainViewModelProvider(repository: PokeRepository) = PokemonDetailViewModel(repository)
}