package com.bragonya.daggerdemo.ui.mainlist

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.bragonya.daggerdemo.model.PokeData
import com.bragonya.daggerdemo.repositories.PokeRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @ViewModelInject constructor(val pokeRepository: PokeRepository): ViewModel() {

    fun updatePokeList() = pokeRepository.getPokemonFromNetwork()
}
