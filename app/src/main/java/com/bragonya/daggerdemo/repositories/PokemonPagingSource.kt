package com.bragonya.daggerdemo.repositories

import androidx.paging.PagingSource
import com.bragonya.daggerdemo.model.PokeData
import com.bragonya.daggerdemo.network.PokeAPI
import com.bragonya.daggerdemo.utils.NUMBER_OF_POKES
import retrofit2.HttpException
import java.io.IOException

class PokemonPagingSource(
    private val service: PokeAPI
): PagingSource<Int, PokeData>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PokeData> {
        val position = params.key ?: 0
        return try {
            val response = service.getPokesFromNetwork(position, NUMBER_OF_POKES)
            LoadResult.Page(
                data = response.results,
                prevKey = if (position == 0) null else position - NUMBER_OF_POKES,
                nextKey = if (response.results.isEmpty()) null else position + NUMBER_OF_POKES
            )
        }catch (exception: IOException){
            return LoadResult.Error(exception)
        } catch (exception: HttpException){
            return LoadResult.Error(exception)
        }
    }
}