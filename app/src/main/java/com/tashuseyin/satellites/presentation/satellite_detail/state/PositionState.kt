package com.tashuseyin.satellites.presentation.satellite_detail.state

import com.tashuseyin.satellites.data.model.model_satellite_position.SatelliteItemPosition

data class PositionState(
    val isLoading: Boolean = false,
    val position: List<SatelliteItemPosition> = emptyList(),
    val error: String = ""
)
