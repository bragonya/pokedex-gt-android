package com.bragonya.daggerdemo.ui.mainlist

import android.util.Log
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bragonya.daggerdemo.R
import com.bragonya.daggerdemo.model.PokeData
import com.bragonya.daggerdemo.utils.inflate
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.poke_holder.view.*

class MainPokemonListAdapter (
    val click: PokeListClickListener
): RecyclerView.Adapter<MainPokemonListAdapter.PokeListHolder>() {

    var list: List<PokeData> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokeListHolder {
        return PokeListHolder(
            parent.context.inflate(R.layout.poke_holder, parent, false),
            click
        )

    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: PokeListHolder, position: Int) {
        holder.bind(position)
    }

    inner class PokeListHolder(
        val view: View,
        val click: PokeListClickListener
    ): RecyclerView.ViewHolder(view) {
        fun bind(position: Int) = view.apply {
            val pokemon = list[position]
            setOnClickListener{ click.onClick(pokemon)}
            pokeName.text = pokemon.name.capitalize()
            Log.d("getting",pokemon.imageURL)
            Picasso.with(view.context).load(pokemon.imageURL).into(pokeImage)
        }
    }

    interface PokeListClickListener {
        fun onClick(pokemon: PokeData)
    }
}