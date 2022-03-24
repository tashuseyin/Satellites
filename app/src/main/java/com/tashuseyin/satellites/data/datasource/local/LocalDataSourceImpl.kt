package com.tashuseyin.satellites.data.datasource.local

import com.tashuseyin.satellites.data.model.model_satellite.SatelliteItem

interface LocalDataSourceImpl {
    suspend fun insertSatellite(satelliteItem: SatelliteItem)
}