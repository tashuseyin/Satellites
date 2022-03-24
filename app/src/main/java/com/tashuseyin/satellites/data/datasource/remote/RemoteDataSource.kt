package com.tashuseyin.satellites.data.datasource.remote

import com.tashuseyin.satellites.data.network.api.SatellitesApi
import com.tashuseyin.satellites.data.network.model.Satellites
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val apiService: SatellitesApi
) : RemoteDataSourceImpl {

    override suspend fun getSatellites(): Satellites {
        return apiService.getSatellites()
    }
}