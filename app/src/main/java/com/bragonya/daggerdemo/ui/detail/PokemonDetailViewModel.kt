package com.bragonya.daggerdemo.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bragonya.daggerdemo.repositories.PokeRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class PokemonDetailViewModel @Inject constructor(private val pokeRepository: PokeRepository): ViewModel() {

    val pokemon = pokeRepository.pokemonDetail

    fun getPokemon(id: Int) = viewModelScope.launch {
        pokeRepository.getPokemonFromNetwork(id)
    }

}