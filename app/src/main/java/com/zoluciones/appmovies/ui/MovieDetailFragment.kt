package com.zoluciones.appmovies.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.zoluciones.appmovies.R
import com.zoluciones.appmovies.databinding.FragmentMovieDetailBinding
import com.zoluciones.appmovies.model.Movie

class MovieDetailFragment : Fragment() {

    private var _binding: FragmentMovieDetailBinding? = null
    private val binding get() = _binding!!
    private val args: MovieDetailFragmentArgs by navArgs()
    private lateinit var movie: Movie

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        movie = args.movie
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMovieDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupData()
    }

    private fun setupData(){
        binding.tvTitle.text = movie.title
        binding.tvDescription.text = movie.description
        binding.tvNote.text = movie.vote.toString()
        binding.tvReleaseDate.text = movie.date
        Glide.with(requireContext()).load(movie.imgUrl).into(binding.imgMovie)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}