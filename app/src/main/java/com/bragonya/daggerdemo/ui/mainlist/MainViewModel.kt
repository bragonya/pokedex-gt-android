package com.bragonya.daggerdemo.ui.mainlist

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.bragonya.daggerdemo.repositories.PokeRepository

class MainViewModel @ViewModelInject constructor(val pokeRepository: PokeRepository): ViewModel() {

    fun updatePokeList() = pokeRepository.getPokemonDetailsFromNetwork()
}
