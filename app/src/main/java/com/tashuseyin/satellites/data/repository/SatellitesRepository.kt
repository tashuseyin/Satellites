package com.tashuseyin.satellites.data.repository

import com.tashuseyin.satellites.data.datasource.remote.RemoteDataSource
import com.tashuseyin.satellites.data.network.model.SatelliteItem
import javax.inject.Inject

class SatellitesRepository @Inject constructor(
    private val remote: RemoteDataSource
) {
    suspend fun getSatellites() = remote.getSatellites()
}