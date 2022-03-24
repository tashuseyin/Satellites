package com.tashuseyin.satellites.presentation.satellite_detail.state

import com.tashuseyin.satellites.data.model.model_satellite.SatelliteItem
import com.tashuseyin.satellites.data.model.model_satellite_detail.SatelliteDetailItem

data class SatelliteDetailState(
    val isLoading: Boolean = false,
    val satellite: SatelliteDetailItem? = null,
    val satelliteItem: SatelliteItem? = null,
    val error: String = ""
)