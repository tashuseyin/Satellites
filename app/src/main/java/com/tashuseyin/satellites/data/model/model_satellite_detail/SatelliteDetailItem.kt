package com.tashuseyin.satellites.data.model.model_satellite_detail


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import com.tashuseyin.satellites.common.Constants

@Entity(tableName = Constants.SATELLITES_DETAIL_TABLE)
data class SatelliteDetailItem(
    @SerializedName("cost_per_launch")
    val costPerLaunch: Int,
    @SerializedName("first_flight")
    val firstFlight: String,
    @SerializedName("height")
    val height: Int,
    @PrimaryKey(autoGenerate = false)
    @SerializedName("id")
    val id: Int,
    @SerializedName("mass")
    val mass: Int
)