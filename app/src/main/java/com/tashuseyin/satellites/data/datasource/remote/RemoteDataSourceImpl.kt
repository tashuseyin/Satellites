package com.tashuseyin.satellites.data.datasource.remote

import com.tashuseyin.satellites.data.model.model_satellite.Satellites

interface RemoteDataSourceImpl {
    suspend fun getSatellites(): Satellites
}