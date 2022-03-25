package com.tashuseyin.satellites.data.datasource.remote

import com.tashuseyin.satellites.data.model.model_satellite.Satellites
import com.tashuseyin.satellites.data.model.model_satellite_detail.SatelliteDetail
import com.tashuseyin.satellites.data.model.model_satellite_position.Result

interface RemoteDataSourceImpl {
    suspend fun getSatellites(): Satellites
    suspend fun getSatelliteById(): SatelliteDetail
    suspend fun getSatellitePosition(): Result
}