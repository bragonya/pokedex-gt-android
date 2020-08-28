package com.bragonya.daggerdemo.ui.detail

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bragonya.daggerdemo.R
import com.bragonya.daggerdemo.model.PokeData
import com.bragonya.daggerdemo.ui.IPokeListClickListener
import com.bragonya.daggerdemo.utils.inflate
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.poke_holder.view.*

class EvolutionListAdapter (
    private val click: IPokeListClickListener
): RecyclerView.Adapter<EvolutionListAdapter.PokeListHolder>() {

    var list = listOf<PokeData>()
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

    override fun onBindViewHolder(holder: PokeListHolder, position: Int) {
        val pokemon = list[position]
        holder.bind(pokemon)
    }

    class PokeListHolder(
        private val view: View,
        private val click: IPokeListClickListener
    ): RecyclerView.ViewHolder(view) {
        fun bind(pokemon: PokeData) = view.apply {
            setOnClickListener{ click.onClick(pokemon)}
            pokeName.text = pokemon.name.capitalize()
            pokemonNumber.text = "No. ${pokemon.pokeNumber}"
            Picasso.with(view.context).load(pokemon.imageURL).placeholder( R.drawable.pokeball_animation ).into(pokeImage)
        }
    }

    override fun getItemCount() = list.size
}