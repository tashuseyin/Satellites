package com.tashuseyin.satellites.data.repository

import com.tashuseyin.satellites.data.datasource.local.LocalDataSource
import com.tashuseyin.satellites.data.datasource.remote.RemoteDataSource
import com.tashuseyin.satellites.data.model.model_satellite_detail.SatelliteDetailItem
import javax.inject.Inject

class SatellitesRepository @Inject constructor(
    private val remote: RemoteDataSource,
    private val local: LocalDataSource
) {
    suspend fun getSatellites() = remote.getSatellites()
    suspend fun getSatelliteById() = remote.getSatelliteById()
    suspend fun insertSatellite(satelliteDetailItem: SatelliteDetailItem) =
        local.insertSatellite(satelliteDetailItem)
}