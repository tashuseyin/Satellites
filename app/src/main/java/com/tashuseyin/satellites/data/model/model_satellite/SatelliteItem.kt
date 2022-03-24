package com.tashuseyin.satellites.data.model.model_satellite


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import com.tashuseyin.satellites.common.Constants

@Entity(tableName = Constants.SATELLITES_TABLE)
data class SatelliteItem(
    @SerializedName("active")
    val active: Boolean,
    @PrimaryKey(autoGenerate = false)
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    var clickSatellite: Int = 0
)