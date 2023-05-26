package com.example.practica_android_avanzado.ui.main.table

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.practica_android_avanzado.databinding.FragmentTableBinding
import com.example.practica_android_avanzado.ui.main.MainActivityViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import androidx.navigation.fragment.findNavController

@AndroidEntryPoint
class FragmentTable : Fragment(), HeroClicked {

    private lateinit var binding: FragmentTableBinding
    private val viewModel : MainActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.getHeros()

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTableBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = FragmentTableAdapter(viewModel.heroes,this)

        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        binding.recyclerView.adapter = adapter

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.mainStatus.collect {
                    when (it) {
                        is MainActivityViewModel.MainStatus.Loading -> {
                            binding.loading.visibility = View.VISIBLE
                        }

                        is MainActivityViewModel.MainStatus.Error -> {
                            binding.loading.visibility = View.GONE
                            Toast.makeText(requireContext(), it.error, Toast.LENGTH_LONG).show()
                        }

                        is MainActivityViewModel.MainStatus.Success -> {
                            binding.loading.visibility = View.GONE
                            adapter.listHeroes = viewModel.heroes
                            adapter.notifyDataSetChanged()
                        }
                    }
                }
            }
        }

    }

    override fun clicked(id: String) {
        findNavController().navigate(
            FragmentTableDirections.actionFragmentTableToFragmentDetail(id)
        )
    }

}