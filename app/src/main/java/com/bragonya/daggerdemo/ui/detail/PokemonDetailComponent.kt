package com.bragonya.daggerdemo.ui.detail

import com.bragonya.daggerdemo.ui.mainlist.MainFragment
import dagger.Subcomponent

@Subcomponent
interface PokemonDetailComponent {
    fun inject(pokemonDetailFragment: PokemonDetailFragment)
}