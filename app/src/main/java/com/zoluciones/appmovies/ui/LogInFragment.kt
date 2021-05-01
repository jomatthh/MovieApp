package com.zoluciones.appmovies.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.zoluciones.appmovies.R
import com.zoluciones.appmovies.databinding.FragmentLogInBinding
import com.zoluciones.appmovies.showToast


class LogInFragment : Fragment() {

    private var _binding: FragmentLogInBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLogInBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnLogIn.setOnClickListener {
            if (isValid()) {
                val action = LogInFragmentDirections.actionLogInFragmentToMovieFragment()
                findNavController().navigate(action)
            } else {
                requireContext().showToast(getString(R.string.log_in_fragment_error))
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun isValid(): Boolean {
        val user = binding.tieUser.text.toString()
        val pass = binding.tiePassword.text.toString()
        if (user != "Admin" || pass != "Password*123") {
            return false
        }
        return true
    }
}