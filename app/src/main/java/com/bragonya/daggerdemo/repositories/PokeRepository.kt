package com.bragonya.daggerdemo.repositories

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.bragonya.daggerdemo.model.PokeData
import com.bragonya.daggerdemo.model.PokemonDetail
import com.bragonya.daggerdemo.network.PokeAPI
import com.bragonya.daggerdemo.utils.NUMBER_OF_POKES
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PokeRepository @Inject constructor(val service: PokeAPI){

    fun getPokemonDetailsFromNetwork(): LiveData<PagingData<PokeData>>{
        return Pager(
            config = PagingConfig(
                pageSize = NUMBER_OF_POKES,
                enablePlaceholders = true
            ),
            pagingSourceFactory =  { PokemonPagingSource(service)}
        ).liveData
    }

    @WorkerThread
    suspend fun getPokemonDetailsFromNetwork(id: Int)  = service.getPokemonDetail(id)

    @WorkerThread
    suspend fun getPokemonSpecie(id: Int) = service.getPokemonSpecies(id)

    @WorkerThread
    suspend fun getPokemonEvolutionChain(id: Int) = service.getEvolutionChain(id)
}