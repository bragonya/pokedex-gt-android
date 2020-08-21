package com.bragonya.daggerdemo.ui.mainlist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.Navigation.findNavController

import com.bragonya.daggerdemo.R
import com.bragonya.daggerdemo.model.PokeData
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.main_fragment.*
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainFragment : Fragment(), MainPokemonListPagingAdapter.PokeListClickListener {

    private val viewModel: MainViewModel by viewModels()
    var adapter = MainPokemonListPagingAdapter(this)
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()

        val fromDetail = navController.currentBackStackEntry
            ?.savedStateHandle?.get<Boolean>("from_detail") ?: false
        if(!fromDetail) {
            viewModel.updatePokeList().observe(viewLifecycleOwner, Observer {
                lifecycleScope.launch {
                    adapter.submitData(it)
                }
            })
        }
    }

    override fun onClick(pokemon: PokeData) {
        val action = MainFragmentDirections.mainToDetail(pokemon)
        navController.navigate(action)
    }

    private fun setupRecyclerView(){
        pokeListRecycler.adapter = adapter
    }

}
