package com.tashuseyin.satellites.presentation.satellite_detail

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.tashuseyin.satellites.common.observeOnce
import com.tashuseyin.satellites.data.model.model_satellite_detail.SatelliteDetailItem
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
        readDatabase()
        setPosition()
    }

    private fun setPosition() {
        lifecycleScope.launch {
            satelliteDetailViewModel.positionState.collect { state ->
                binding.apply {
                    if (state.error.isNotBlank()) {
                        errorText.text = state.error
                    }
                    progress.isVisible = state.isLoading

                    if (state.position.isNotEmpty()) {
                        lastPositionX.text = state.position[0].positions[0].posX.toString()
                        lastPositionY.text = state.position[0].positions[0].posY.toString()
                    }
                }
            }
        }
    }


    private fun requestApi() {
        Log.d("TAG", "API")
        satelliteDetailViewModel.getSatelliteDetailItem()
        lifecycleScope.launch {
            satelliteDetailViewModel.state.collect { state ->
                binding.apply {
                    if (state.error.isNotBlank()) {
                        errorText.text = state.error
                    }
                    progress.isVisible = state.isLoading

                    if (state.satellite != null) {
                        setDataSatelliteDetailItem(state.satellite)
                    }
                }
            }
        }
    }

    private fun setDataSatelliteDetailItem(satelliteDetailItem: SatelliteDetailItem) {
        binding.apply {
            if (satelliteDetailViewModel.satelliteItem != null) {
                satelliteName.text = satelliteDetailViewModel.satelliteItem!!.name
            }
            satelliteCost.text = satelliteDetailItem.costPerLaunch.toString()
            satelliteHeight.text = satelliteDetailItem.height.toString()
            satelliteMass.text = satelliteDetailItem.mass.toString()
            satelliteDate.text = satelliteDetailItem.firstFlight
        }

    }


    private fun readDatabase() {
        lifecycleScope.launch {
            satelliteDetailViewModel.readSatelliteDetailDatabase?.observeOnce(viewLifecycleOwner) { satelliteDetailItem ->
                if (satelliteDetailItem != null) {
                    Log.d("TAG", "DATABASE")
                    setDataSatelliteDetailItem(satelliteDetailItem)
                } else {
                    requestApi()
                }
            }
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}