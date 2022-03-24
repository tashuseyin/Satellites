package com.tashuseyin.satellites.presentation.satellite_list.state

import com.tashuseyin.satellites.data.model.SatelliteItem

data class SatelliteListState(
    val isLoading: Boolean = false,
    val satelliteList: List<SatelliteItem> = emptyList(),
    val error: String = ""
)