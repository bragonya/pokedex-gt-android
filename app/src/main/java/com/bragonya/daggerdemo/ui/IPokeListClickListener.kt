package com.bragonya.daggerdemo.ui

import com.bragonya.daggerdemo.model.PokeData

//SAM Interface to support lambdas :)
fun interface IPokeListClickListener{
    fun onClick(pokemon: PokeData)
}