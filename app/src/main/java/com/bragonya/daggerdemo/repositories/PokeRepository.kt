package com.bragonya.daggerdemo.repositories

import androidx.annotation.WorkerThread
import androidx.lifecycle.MutableLiveData
import com.bragonya.daggerdemo.model.PokeData
import com.bragonya.daggerdemo.model.PokemonDetail
import com.bragonya.daggerdemo.network.PokeAPI
import com.bragonya.daggerdemo.utils.NUMBER_OF_POKES
import javax.inject.Inject

class PokeRepository @Inject constructor(val service: PokeAPI){

    val listOfPokemon = MutableLiveData<List<PokeData>>()
    val pokemonDetail = MutableLiveData<PokemonDetail>()

    @WorkerThread
    suspend fun retrievePokesFromNetwork(){
        val pokeRoot = service.getPokesFromNetwork(NUMBER_OF_POKES)
        listOfPokemon.postValue(pokeRoot.body()?.results ?: emptyList())
    }

    @WorkerThread
    suspend fun getPokemonFromNetwork(id: Int){
        val pokemon = service.getPokemonDetail(id)
        pokemonDetail.postValue(pokemon)
    }
}