package com.bragonya.daggerdemo.ui.mainlist

import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bragonya.daggerdemo.R
import com.bragonya.daggerdemo.model.PokeData
import com.bragonya.daggerdemo.utils.inflate
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.poke_holder.view.*

class MainPokemonListPagingAdapter (
    private val click: PokeListClickListener
): PagingDataAdapter<PokeData, MainPokemonListPagingAdapter.PokeListHolder>(POKEMON_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokeListHolder {
        return PokeListHolder(
            parent.context.inflate(R.layout.poke_holder, parent, false),
            click
        )
    }

    override fun onBindViewHolder(holder: PokeListHolder, position: Int) {
        val pokemon = getItem(position)
        if (pokemon != null)
            holder.bind(pokemon)
    }

    inner class PokeListHolder(
        private val view: View,
        private val click: PokeListClickListener
    ): RecyclerView.ViewHolder(view) {
        fun bind(pokemon: PokeData) = view.apply {
            setOnClickListener{ click.onClick(pokemon)}
            pokeName.text = pokemon.name.capitalize()
            pokemonNumber.text = "No. ${pokemon.pokeNumber}"
            Picasso.with(view.context).load(pokemon.imageURL).placeholder( R.drawable.pokeball_animation ).into(pokeImage)
        }
    }

    interface PokeListClickListener {
        fun onClick(pokemon: PokeData)
    }

    companion object {
        private val POKEMON_COMPARATOR = object : DiffUtil.ItemCallback<PokeData>() {
            override fun areItemsTheSame(oldItem: PokeData, newItem: PokeData): Boolean =
                oldItem.pokeNumber == newItem.pokeNumber

            override fun areContentsTheSame(oldItem: PokeData, newItem: PokeData): Boolean =
                oldItem == newItem
        }
    }
}