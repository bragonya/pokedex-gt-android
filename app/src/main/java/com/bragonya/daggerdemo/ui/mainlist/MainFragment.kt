package com.bragonya.daggerdemo.ui.mainlist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.Navigation.findNavController

import com.bragonya.daggerdemo.R
import com.bragonya.daggerdemo.model.PokeData
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.main_fragment.*
import javax.inject.Inject

@AndroidEntryPoint
class MainFragment : Fragment(), MainPokemonListAdapter.PokeListClickListener {

    private val viewModel: MainViewModel by viewModels()
    var adapter = MainPokemonListAdapter(this)
    lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        (requireActivity() as AppCompatActivity).run {
            supportActionBar?.setDisplayHomeAsUpEnabled(false)
        }
        navController = findNavController(requireActivity(), R.id.mainListFragment)
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    private fun setupRecyclerView(){
        pokeListRecycler.adapter = adapter
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupRecyclerView()
        viewModel.pokeList.observe(viewLifecycleOwner, Observer {
            adapter.list = it
        })
        viewModel.updatePokeList()
    }

    override fun onClick(pokemon: PokeData) {
        navController.navigate(R.id.main_to_detail)
    }

}
