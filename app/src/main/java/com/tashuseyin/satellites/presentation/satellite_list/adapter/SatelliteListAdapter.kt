package com.tashuseyin.satellites.presentation.satellite_list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.tashuseyin.satellites.data.model.model_satellite.SatelliteItem
import com.tashuseyin.satellites.databinding.SatelliteRowLayoutBinding

class SatelliteListAdapter :
    ListAdapter<SatelliteItem, SatelliteListViewHolder>(SatelliteItemDiffCallback()) {
    var onItemClickListener: ((SatelliteItem) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SatelliteListViewHolder {
        val binding =
            SatelliteRowLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SatelliteListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SatelliteListViewHolder, position: Int) {
        holder.bind(getItem(position), onItemClickListener)
    }
}

class SatelliteItemDiffCallback : DiffUtil.ItemCallback<SatelliteItem>() {
    override fun areItemsTheSame(oldItem: SatelliteItem, newItem: SatelliteItem) =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: SatelliteItem, newItem: SatelliteItem) =
        oldItem == newItem
}