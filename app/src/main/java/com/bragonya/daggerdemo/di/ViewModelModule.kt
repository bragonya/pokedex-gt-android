package com.bragonya.daggerdemo.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bragonya.daggerdemo.ui.detail.PokemonDetailViewModel
import com.bragonya.daggerdemo.ui.mainlist.MainViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule{
    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    internal abstract fun mainViewModel(viewModel: MainViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(PokemonDetailViewModel::class)
    internal abstract fun detailViewModel(viewModel: PokemonDetailViewModel): ViewModel
}