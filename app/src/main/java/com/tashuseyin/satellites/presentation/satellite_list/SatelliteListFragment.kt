package com.tashuseyin.satellites.presentation.satellite_list

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.SearchView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.tashuseyin.satellites.R
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
        searching()
    }

    private fun searching() {
        binding.search.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                binding.search.clearFocus()
                if (query != null) {
                    searchSatelliteByName(query)
                }
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText != null) {
                    searchSatelliteByName(newText)
                }
                return false
            }
        })
    }

    private fun searchSatelliteByName(name: String) {
        val searchQuery = "%$name%"
        satelliteViewModel.searchSatelliteByNameResult(searchQuery)
            .observe(viewLifecycleOwner) { satelliteItem ->
                adapter.submitList(satelliteItem)
            }
    }

    private fun observeViewModel() {
        lifecycleScope.launch {
            satelliteViewModel.state.collect { state ->
                if (state.satelliteList.isNotEmpty()) {
                    adapter.submitList(state.satelliteList)
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
        adapter.onItemClickListener = { satelliteItem ->
            if (satelliteItem.active) {
                findNavController().navigate(
                    SatelliteListFragmentDirections.actionSatelliteListFragmentToSatelliteDetailFragment(
                        satelliteItem
                    )
                )
            } else {
                showAlertDialog()
            }
        }
    }

    private fun showAlertDialog() {
        val alertDialogBinding = layoutInflater.inflate(R.layout.alert_dialog, null)
        val alertDialog = Dialog(requireContext())
        alertDialog.setContentView(alertDialogBinding)
        alertDialog.setCancelable(true)
        alertDialog.window?.setBackgroundDrawableResource(R.drawable.dialog_bg)
        alertDialog.show()

        val buttonOk = alertDialogBinding.findViewById<Button>((R.id.ok))
        buttonOk.setOnClickListener {
            alertDialog.dismiss()
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}