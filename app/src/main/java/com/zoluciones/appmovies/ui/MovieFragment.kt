package com.zoluciones.appmovies.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.content.ContextCompat
import androidx.core.view.iterator
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.zoluciones.appmovies.Preferences
import com.zoluciones.appmovies.R
import com.zoluciones.appmovies.Result
import com.zoluciones.appmovies.databinding.FragmentMovieBinding
import com.zoluciones.appmovies.model.Movie
import com.zoluciones.appmovies.showToast
import com.zoluciones.appmovies.ui.adapter.MovieAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieFragment : Fragment(), MovieAdapter.OnMovieAdapterListener {
    companion object {
        const val KEY = "f46b58478f489737ad5a4651a4b25079"
    }

    private val viewModel: MainViewModel by viewModels()
    private var _binding: FragmentMovieBinding? = null
    private val binding get() = _binding!!
    private val adapter by lazy {
        MovieAdapter(emptyList(), this)
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMovieBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupAdapter()
        setupMoviesObserver(page = 1)
    }

    private fun setupAdapter() {
        binding.rvMovies.layoutManager = LinearLayoutManager(requireContext())
        binding.rvMovies.adapter = adapter
    }

    private fun setupLocalMovieObserver(page: Int) {
        viewModel.fetchLocalMovies(page).observe(viewLifecycleOwner, Observer { result ->
            when (result) {
                is Result.Success -> {
                    val movies = result.data
                    adapter.update(movies)
                }
                is Result.Failure -> {
                }
            }
        })
    }


    private fun setupMoviesObserver(page: Int) {
        binding.progressBar.visibility = View.VISIBLE
        viewModel.fetchMovies(page, KEY).observe(viewLifecycleOwner, { result ->
            when (result) {
                is Result.Success -> {
                    binding.progressBar.visibility = View.GONE
                    val movies = result.data.movies
                    createPaginate(result.data.totalPage)
                    adapter.update(movies)
                    saveMovies(movies, page)
                    savePage(result.data.totalPage)
                }
                is Result.Failure -> {
                    val totalPage = Preferences.getPage(requireContext().applicationContext)
                    createPaginate(totalPage)
                    setupLocalMovieObserver(page)
                    binding.progressBar.visibility = View.GONE
                    requireContext().showToast(
                            result.exception.message ?: "Error al traer información"
                    )
                }
            }
        })
    }

    private fun saveMovies(movies: List<Movie>, page: Int) {
        viewModel.addMovies(movies, page)
    }

    private fun savePage(page: Int) {
        Preferences.setPage(requireContext().applicationContext, page)
    }

    private fun createPaginate(totalPaginate: Int) {
        if (binding.panelPaginate.iterator().hasNext()) return
        for (index in 1..totalPaginate) {
            createButtonPaginate(index)
        }
    }

    private fun createButtonPaginate(index: Int) {
        val button = Button(requireContext())
        button.id = index
        button.text = index.toString()
        button.setOnClickListener {
            setupMoviesObserver(it.id)
        }
        binding.panelPaginate.addView(button)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun itemClick(movie: Movie) {
        val action = MovieFragmentDirections.actionMovieFragmentToMovieDetailFragment(movie)
        findNavController().navigate(action)
    }
}