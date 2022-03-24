package com.tashuseyin.satellites.data.datasource.local

import com.tashuseyin.satellites.data.model.SatelliteItem

interface LocalDataSourceImpl {
    suspend fun insertSatellite(satelliteItem: SatelliteItem)
}