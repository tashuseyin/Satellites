package com.tashuseyin.satellites.presentation.satellite_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.tashuseyin.satellites.databinding.FragmentSatelliteListBinding
import com.tashuseyin.satellites.presentation.satellite_list.adapter.SatelliteListAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


@AndroidEntryPoint
class SatelliteListFragment : Fragment() {
    private val satelliteViewModel: SatelliteViewModel by viewModels()
    private val adapter = SatelliteListAdapter()
    private var _binding: FragmentSatelliteListBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSatelliteListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navigateDetailFragment()
        observeViewModel()
    }

    private fun observeViewModel() {
        lifecycleScope.launch {
            satelliteViewModel.state.collect { state ->
                if (state.satelliteList.isNotEmpty()) {
                    adapter.setData(state.satelliteList)
                    binding.recyclerview.adapter = adapter
                }
                binding.progress.isVisible = state.isLoading
                if (state.error.isNotBlank()) {
                    binding.errorText.text = state.error
                }
            }
        }
    }

    private fun navigateDetailFragment() {
        adapter.onItemClickListener = { satelliteId ->
            findNavController().navigate(
                SatelliteListFragmentDirections.actionSatelliteListFragmentToSatelliteDetailFragment(
                    satelliteId
                )
            )
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}