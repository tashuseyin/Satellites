package com.tashuseyin.satellites.data.model.model_satellite


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class SatelliteItem(
    @SerializedName("active")
    val active: Boolean,
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String
) : Parcelable