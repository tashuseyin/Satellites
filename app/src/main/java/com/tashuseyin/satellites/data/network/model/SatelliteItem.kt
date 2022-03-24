package com.tashuseyin.satellites.data.network.model


import com.google.gson.annotations.SerializedName

data class SatelliteItem(
    @SerializedName("active")
    val active: Boolean,
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String
)