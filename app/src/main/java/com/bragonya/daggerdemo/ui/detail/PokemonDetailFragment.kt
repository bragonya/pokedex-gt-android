package com.bragonya.daggerdemo.ui.detail


import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.bragonya.daggerdemo.R
import com.bragonya.daggerdemo.model.*
import com.bragonya.daggerdemo.utils.*
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.detail_fragment.*
import kotlinx.android.synthetic.main.main_fragment.*


@AndroidEntryPoint
class PokemonDetailFragment : Fragment(), EvolutionListAdapter.PokeListClickListener {

    private val viewModel: PokemonDetailViewModel by viewModels()
    lateinit var navController: NavController
    val args: PokemonDetailFragmentArgs by navArgs()
    val adapter = EvolutionListAdapter(this)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        (requireActivity() as AppCompatActivity).run {
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            setHasOptionsMenu(true)
        }
        navController = Navigation.findNavController(requireActivity(),
            R.id.mainListFragment
        )
        navController.previousBackStackEntry!!.savedStateHandle.set<Boolean>("from_detail", true)
        return inflater.inflate(R.layout.detail_fragment, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val callback: OnBackPressedCallback =
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    navController.popBackStack()
                }
            }
        requireActivity().onBackPressedDispatcher.addCallback(this, callback)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        viewModel.pokemon.observe(viewLifecycleOwner, Observer {
            updateStatsAndPhoto(it.pokemonDetail)
            updateEvolutionChain(it.pokemonEvolutionChain.chain)
            updateColor(it.pokemonSpecie)
        })
        viewModel.getPokemon(args.PokeData.pokeNumber)
        favorite.setOnClickListener{
            Toast.makeText(view.context, "You caught an ${args.PokeData.name.capitalize()}", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            android.R.id.home -> navController.popBackStack()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun updateStatsAndPhoto(detail: PokemonDetail){
        name.text = detail.name
        hpBar.progress = detail.getStatValue(HP)
        barAttack.progress = detail.getStatValue(ATTACK)
        barDefense.progress = detail.getStatValue(DEFENSE)
        barSpecialAttack.progress = detail.getStatValue(SPECIAL_ATTACK)
        barSpecialDefense.progress = detail.getStatValue(SPECIAL_DEFENSE)
        Picasso.with(view?.context).load(detail.getBackImage()).into(back_image)
        Picasso.with(view?.context).load(detail.getFrontImage()).into(front_image)
    }

    private fun setupRecyclerView(){
        recycler_evolution.adapter = adapter
    }

    private fun updateEvolutionChain(pokemonEvolutionChain: EvolutionChain) {
        val myEvolutions = listOf(pokemonEvolutionChain.species) + evolution(pokemonEvolutionChain.evolvesTo)
        adapter.list = myEvolutions
    }

    private fun updateColor(pokemonSpecie: PokemonSpecie) {
        //parent.setBackgroundColor(Color.parseColor(pokemonSpecie.color.name))
    }

    private fun evolution(evolutions: List<Evolution>): List<PokeData>{
        return if( evolutions.isEmpty() )
            evolutions.map { it.species }
        else {
            val list = evolutions.map { evolution(it.evolvesTo) }
            evolutions.map { it.species } + list.flatten()
        }
    }

    override fun onClick(pokemon: PokeData) {
        TODO("Not yet implemented")
    }
}
