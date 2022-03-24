package com.tashuseyin.satellites.data.model.model_satellite_position

import com.google.gson.annotations.SerializedName

data class SatelliteItemPosition(
    @SerializedName("id")
    val id: String,
    @SerializedName("positions")
    val positions: List<Position>
)
