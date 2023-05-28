package com.example.practica_android_avanzado.ui.main.detail

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.navArgs
import com.example.practica_android_avanzado.R
import com.example.practica_android_avanzado.databinding.FragmentDetailsBinding
import com.example.practica_android_avanzado.ui.main.MainActivity
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class FragmentDetail() : Fragment(), OnMapReadyCallback {

    private lateinit var binding: FragmentDetailsBinding
    private val viewModel : DetailViewModel by viewModels()
    private val args: FragmentDetailArgs by navArgs()
    private lateinit var map: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.getHero(args.heroId)
        viewModel.getHeroLocation(args.heroId)
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

        val mapFragment = childFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.detailStatus.collect {
                    when (it) {
                        is DetailViewModel.DetailStatus.Loading -> {
                            binding.loading.visibility = View.VISIBLE
                        }

                        is DetailViewModel.DetailStatus.Error -> {
                            binding.loading.visibility = View.GONE
                            Toast.makeText(requireContext(), it.error, Toast.LENGTH_LONG).show()
                        }

                        is DetailViewModel.DetailStatus.Success -> {
                            binding.loading.visibility = View.GONE
                            Picasso.get().load(it.hero.photo).placeholder(R.drawable.baseline_person_24).into(binding.heroImage)
                            (requireActivity() as MainActivity).binding.toolbar.title = it.hero.name
                            binding.heroName.text = it.hero.name
                            binding.heroDescription.text = it.hero.description
                            updateUIFav()
                        }
                    }
                }
            }
        }

        binding.heroFav.setOnClickListener {
            viewModel.hero.favorite = !viewModel.hero.favorite
            viewModel.updateFav()
            updateUIFav()
        }

    }

    private fun updateUIFav() {
        if(viewModel.hero.favorite) binding.heroFav.setImageResource(R.drawable.baseline_favorite)
        else binding.heroFav.setImageResource(R.drawable.baseline_favorite_border_24)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        val hasPermission = ActivityCompat.checkSelfPermission(
            context,
            Manifest.permission.ACCESS_COARSE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
            context,
            Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED

        if (!hasPermission) {
            val permissionLauncher =
                registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
                    if (!isGranted) {
                        Toast.makeText(
                            requireContext(),
                            "No podemos mostrar la localizacion sin permiso",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }

            permissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
        }
    }

    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap
        val location = LatLng(viewModel.heroLocation.latitud, viewModel.heroLocation.longitud)
        map.addMarker(
            MarkerOptions()
                .position(location)
        )
        map.moveCamera(CameraUpdateFactory.newLatLng(location))
        }

    }


