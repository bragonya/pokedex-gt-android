package com.bragonya.daggerdemo.ui.detail


import android.os.Bundle
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
import com.bragonya.daggerdemo.model.PokemonDetail
import com.bragonya.daggerdemo.utils.*
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_pokemon_detail.*


@AndroidEntryPoint
class PokemonDetailFragment : Fragment() {

    private val viewModel: PokemonDetailViewModel by viewModels()
    lateinit var navController: NavController
    val args: PokemonDetailFragmentArgs by navArgs()

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
        return inflater.inflate(R.layout.fragment_pokemon_detail, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val callback: OnBackPressedCallback =
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    navController.navigateUp()
                }
            }
        requireActivity().onBackPressedDispatcher.addCallback(this, callback)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.pokemon.observe(viewLifecycleOwner, Observer { updateUI(it) })
        viewModel.getPokemon(args.PokeData.pokeNumber)
        favorite.setOnClickListener{
            Toast.makeText(view.context, "You caught an ${args.PokeData.name.capitalize()}", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            android.R.id.home -> navController.navigateUp()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun updateUI(detail: PokemonDetail){
        name.text = detail.name
        hpBar.progress = detail.getStatValue(HP)
        barAttack.progress = detail.getStatValue(ATTACK)
        barDefense.progress = detail.getStatValue(DEFENSE)
        barSpecialAttack.progress = detail.getStatValue(SPECIAL_ATTACK)
        barSpecialDefense.progress = detail.getStatValue(SPECIAL_DEFENSE)
        Picasso.with(view?.context).load(detail.getBackImage()).into(back_image)
        Picasso.with(view?.context).load(detail.getFrontImage()).into(front_image)
    }
}
