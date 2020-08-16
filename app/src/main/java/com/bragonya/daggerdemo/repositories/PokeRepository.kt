package com.bragonya.daggerdemo.repositories

import androidx.annotation.WorkerThread
import androidx.lifecycle.MutableLiveData
import com.bragonya.daggerdemo.model.PokeData
import com.bragonya.daggerdemo.network.PokeAPI
import com.bragonya.daggerdemo.utils.NUMBER_OF_POKES
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Inject

class PokeRepository @Inject constructor(val service: PokeAPI){

    val listOfPokemon = MutableLiveData<List<PokeData>>()

    @WorkerThread
    suspend fun retrievePokesFromNetwork(){
        val pokeRoot = service.getPokesFromNetwork(NUMBER_OF_POKES)
        listOfPokemon.postValue(pokeRoot.body()?.results ?: emptyList())
    }
}