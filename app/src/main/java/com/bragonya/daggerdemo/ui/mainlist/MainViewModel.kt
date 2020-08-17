package com.bragonya.daggerdemo.ui.mainlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bragonya.daggerdemo.repositories.PokeRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(private val pokeRepository: PokeRepository): ViewModel() {

    val pokeList = pokeRepository.listOfPokemon

    fun updatePokeList() = viewModelScope.launch {
        pokeRepository.retrievePokesFromNetwork()
    }

}
