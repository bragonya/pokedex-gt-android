package com.bragonya.daggerdemo.ui.mainlist

import androidx.lifecycle.ViewModel
import com.bragonya.daggerdemo.repositories.PokeRepository
import javax.inject.Inject

class MainViewModel @Inject constructor(val pokeRepository: PokeRepository): ViewModel() {

    val pokeList = pokeRepository.listOfPokemon

    fun updatePokeList() = pokeRepository.fetchPokeListData()

}
