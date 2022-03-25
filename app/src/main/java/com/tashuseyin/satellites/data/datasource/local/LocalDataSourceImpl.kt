package com.tashuseyin.satellites.data.datasource.local

import androidx.lifecycle.LiveData
import com.tashuseyin.satellites.data.model.model_satellite.SatelliteItem
import com.tashuseyin.satellites.data.model.model_satellite_detail.SatelliteDetailItem

interface LocalDataSourceImpl {
    suspend fun insertDetailSatellites(satelliteDetailItem: SatelliteDetailItem)
    suspend fun insertSatellitesItem(satelliteItem: SatelliteItem)
    fun getSatellitesDetailDatabase(id: Int): LiveData<SatelliteDetailItem>
    fun searchSatelliteByName(name: String): LiveData<List<SatelliteItem>>
}