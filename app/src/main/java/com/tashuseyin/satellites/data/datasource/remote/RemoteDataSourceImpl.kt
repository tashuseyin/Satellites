package com.tashuseyin.satellites.data.datasource.remote

import com.tashuseyin.satellites.data.model.Satellites

interface RemoteDataSourceImpl {
    suspend fun getSatellites(): Satellites
}