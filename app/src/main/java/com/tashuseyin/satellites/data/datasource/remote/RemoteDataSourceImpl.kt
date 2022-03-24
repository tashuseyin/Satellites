package com.tashuseyin.satellites.data.datasource.remote

import com.tashuseyin.satellites.data.model.model_satellite.Satellites
import com.tashuseyin.satellites.data.model.model_satellite_detail.SatelliteDetail

interface RemoteDataSourceImpl {
    suspend fun getSatellites(): Satellites
    suspend fun getSatelliteById(): SatelliteDetail
}