package com.tashuseyin.satellites.presentation.satellite_list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tashuseyin.satellites.data.model.model_satellite.SatelliteItem
import com.tashuseyin.satellites.databinding.SatelliteRowLayoutBinding

class SatelliteListAdapter : RecyclerView.Adapter<SatelliteListViewHolder>() {
    private var satelliteList = emptyList<SatelliteItem>()
    var onItemClickListener: ((SatelliteItem) -> Unit)? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SatelliteListViewHolder {
        val binding =
            SatelliteRowLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SatelliteListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SatelliteListViewHolder, position: Int) {
        holder.bind(satelliteList[position], onItemClickListener)
    }

    override fun getItemCount() = satelliteList.size

    fun setData(newSatelliteList: List<SatelliteItem>) {
        this.satelliteList = newSatelliteList
        this.notifyDataSetChanged()
    }
}