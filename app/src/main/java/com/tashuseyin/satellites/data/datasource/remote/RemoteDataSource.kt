package com.tashuseyin.satellites.data.datasource.remote

import com.tashuseyin.satellites.data.network.SatellitesApi
import com.tashuseyin.satellites.data.model.model_satellite.Satellites
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val apiService: SatellitesApi
) : RemoteDataSourceImpl {

    override suspend fun getSatellites(): Satellites {
        return apiService.getSatellites()
    }
}