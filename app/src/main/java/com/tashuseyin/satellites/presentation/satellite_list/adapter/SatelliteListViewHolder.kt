package com.tashuseyin.satellites.presentation.satellite_list.adapter

import android.graphics.Color
import androidx.recyclerview.widget.RecyclerView
import com.tashuseyin.satellites.R
import com.tashuseyin.satellites.data.network.model.SatelliteItem
import com.tashuseyin.satellites.databinding.SatelliteRowLayoutBinding

class SatelliteListViewHolder(private val binding: SatelliteRowLayoutBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(satelliteItem: SatelliteItem) {
        binding.satelliteName.text = satelliteItem.name
        binding.satelliteStateName.text = if (satelliteItem.active) "active" else "inactive"
        binding.satelliteStateName.setTextColor(if (satelliteItem.active) Color.GREEN else Color.RED)
        if (!satelliteItem.active) {
            binding.satelliteState.setImageResource(R.drawable.satellite_state_red)
        } else {
            binding.satelliteState.setImageResource(
                R.drawable.satellite_state_green
            )
        }


    }
}