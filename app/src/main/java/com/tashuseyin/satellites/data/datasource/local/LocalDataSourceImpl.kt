package com.tashuseyin.satellites.data.datasource.local

import com.tashuseyin.satellites.data.model.model_satellite_detail.SatelliteDetailItem

interface LocalDataSourceImpl {
    suspend fun insertSatellite(satelliteDetailItem: SatelliteDetailItem)
}