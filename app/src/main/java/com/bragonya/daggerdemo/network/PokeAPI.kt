package com.bragonya.daggerdemo.network

import com.bragonya.daggerdemo.model.PokeRoot
import com.bragonya.daggerdemo.model.PokemonDetail
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokeAPI {

    @GET("/api/v2/pokemon")
    suspend fun getPokesFromNetwork(@Query("limit") limit: Int): Response<PokeRoot>

    @GET("/api/v2/pokemon/{id}")
    suspend fun getPokemonDetail(@Path("id") pokemonNumber: Int): PokemonDetail
}