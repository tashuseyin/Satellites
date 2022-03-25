package com.tashuseyin.satellites.data.model.model_satellite


import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import com.tashuseyin.satellites.common.Constants
import kotlinx.parcelize.Parcelize

@Entity(tableName = Constants.SATELLITES_ITEM_TABLE)
@Parcelize
data class SatelliteItem(
    @SerializedName("active")
    val active: Boolean,
    @PrimaryKey(autoGenerate = false)
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String
) : Parcelable