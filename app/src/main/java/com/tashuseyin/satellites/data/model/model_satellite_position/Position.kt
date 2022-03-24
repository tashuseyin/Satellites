package com.tashuseyin.satellites.data.model.model_satellite_position


import com.google.gson.annotations.SerializedName

data class Position(
    @SerializedName("posX")
    val posX: Double,
    @SerializedName("posY")
    val posY: Double
)