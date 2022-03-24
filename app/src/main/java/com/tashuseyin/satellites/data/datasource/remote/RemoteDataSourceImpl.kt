package com.tashuseyin.satellites.data.datasource.remote

import com.tashuseyin.satellites.data.network.model.Satellites

interface RemoteDataSourceImpl {
    suspend fun getSatellites(): Satellites
}