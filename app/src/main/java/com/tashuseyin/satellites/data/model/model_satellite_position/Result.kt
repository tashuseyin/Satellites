package com.tashuseyin.satellites.data.model.model_satellite_position


import com.google.gson.annotations.SerializedName

data class Result(
    @SerializedName("list")
    val list: List<SatelliteItemPosition>
)