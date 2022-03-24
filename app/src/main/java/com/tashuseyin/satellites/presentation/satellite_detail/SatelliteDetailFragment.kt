package com.tashuseyin.satellites.presentation.satellite_detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.tashuseyin.satellites.databinding.FragmentSatelliteDetailBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SatelliteDetailFragment : Fragment() {
    private val satelliteDetailViewModel: SatelliteDetailViewModel by viewModels()
    private var _binding: FragmentSatelliteDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSatelliteDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeModel()
    }

    private fun observeModel() {
        lifecycleScope.launch {
            satelliteDetailViewModel.state.collect { state ->
                binding.apply {
                    if (state.error.isNotBlank()) {
                        errorText.text = state.error
                    }
                    progress.isVisible = state.isLoading

                    if (state.satellite != null) {
                        satelliteCost.text = state.satellite.costPerLaunch.toString()
                        val heightMass =
                            state.satellite.height.toString() + "/" + state.satellite.mass.toString()
                        satelliteHeightMass.text = heightMass
                        satelliteDate.text = state.satellite.firstFlight
                    }
                    if (state.satelliteItem != null) {
                        satelliteName.text = state.satelliteItem.name
                    }
                }
            }
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}