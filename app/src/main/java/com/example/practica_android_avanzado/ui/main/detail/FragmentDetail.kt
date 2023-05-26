package com.example.practica_android_avanzado.ui.main.detail

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
import androidx.navigation.fragment.navArgs
import com.example.practica_android_avanzado.R
import com.example.practica_android_avanzado.databinding.FragmentDetailsBinding
import com.example.practica_android_avanzado.ui.main.MainActivity
import com.example.practica_android_avanzado.ui.main.MainActivityViewModel
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

//import com.squareup.picasso.Picasso


@AndroidEntryPoint
class FragmentDetail() : Fragment() {

    private lateinit var binding: FragmentDetailsBinding
    private val viewModel : DetailViewModel by viewModels()
    private val args: FragmentDetailArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.getHero(args.heroId)

//        requireActivity().onBackPressedDispatcher.addCallback(this) {
//            goBack()
//        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailsBinding.inflate(inflater)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.detailStatus.collect {
                    when (it) {
                        is DetailViewModel.Detailtatus.Loading -> {
                            binding.loading.visibility = View.VISIBLE
                        }

                        is DetailViewModel.Detailtatus.Error -> {
                            binding.loading.visibility = View.GONE
                            Toast.makeText(requireContext(), it.error, Toast.LENGTH_LONG).show()
                        }

                        is DetailViewModel.Detailtatus.Success -> {
                            binding.loading.visibility = View.GONE
                            Picasso.get().load(it.hero.photo).placeholder(R.drawable.baseline_person_24).into(binding.heroImage)
                            (requireActivity() as MainActivity).binding.toolbar.title = it.hero.name
                            binding.heroName.text = it.hero.name
                            binding.heroDescription.text = it.hero.description
                            if(it.hero.favorite) binding

                        }
                    }
                }
            }
        }


    }

//    private fun goBack(){
//        requireActivity().supportFragmentManager.popBackStack()
//
//    }



}