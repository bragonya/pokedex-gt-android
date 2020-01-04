package com.bragonya.daggerdemo.ui.mainlist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.Navigation.findNavController

import com.bragonya.daggerdemo.R
import com.bragonya.daggerdemo.application.App
import com.bragonya.daggerdemo.model.PokeData
import kotlinx.android.synthetic.main.main_fragment.*
import javax.inject.Inject

class MainFragment : Fragment(), MainPokemonListAdapter.PokeListClickListener {

    @Inject
    lateinit var viewModel: MainViewModel
    var adapter = MainPokemonListAdapter(this)
    lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        injection()
        (requireActivity() as AppCompatActivity).run {
            supportActionBar?.setDisplayHomeAsUpEnabled(false)
        }
        navController = findNavController(requireActivity(), R.id.mainListFragment)
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    private fun setupRecyclerView(){
        pokeListRecycler.adapter = adapter
    }

    private fun injection(){
        App.rootFactory.getMainFragment().inject(this)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupRecyclerView()
        viewModel.pokeList.observe(this, Observer {
            adapter.list = it
        })
        viewModel.updatePokeList()
    }

    override fun onClick(pokemon: PokeData) {
        navController.navigate(R.id.main_to_detail)
    }

}
