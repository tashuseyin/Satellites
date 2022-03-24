package com.tashuseyin.satellites.data.datasource.remote

import com.tashuseyin.satellites.data.model.model_satellite.Satellites
import com.tashuseyin.satellites.data.model.model_satellite_detail.SatelliteDetail
import com.tashuseyin.satellites.data.network.SatellitesApi
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val apiService: SatellitesApi
) : RemoteDataSourceImpl {

    override suspend fun getSatellites(): Satellites {
        return apiService.getSatellites()
    }

    override suspend fun getSatelliteById(): SatelliteDetail {
        return apiService.getSatelliteById()
    }
}