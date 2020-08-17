package com.bragonya.daggerdemo.application

import android.app.Application
import com.bragonya.daggerdemo.di.ViewModelModule
import com.bragonya.daggerdemo.network.NetworkModule
import com.bragonya.daggerdemo.ui.detail.PokemonDetailComponent
import com.bragonya.daggerdemo.ui.mainlist.MainFragmentComponent
import dagger.Component

@Component(modules = [NetworkModule::class, ViewModelModule::class])
interface RootComponent{
    fun getMainFragment(): MainFragmentComponent
    fun getDetailFragment(): PokemonDetailComponent
}


object App{
    val rootFactory = DaggerRootComponent.create()
}
