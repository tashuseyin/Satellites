package com.tashuseyin.satellites.data.repository

import com.tashuseyin.satellites.data.datasource.local.LocalDataSource
import com.tashuseyin.satellites.data.datasource.remote.RemoteDataSource
import com.tashuseyin.satellites.data.model.model_satellite.SatelliteItem
import com.tashuseyin.satellites.data.model.model_satellite_detail.SatelliteDetailItem
import javax.inject.Inject

class SatellitesRepository @Inject constructor(
    private val remote: RemoteDataSource,
    private val local: LocalDataSource
) {
    /** Remote Data Source **/
    suspend fun getSatellites() = remote.getSatellites()
    suspend fun getSatelliteById() = remote.getSatelliteById()

    /** Local Data Source **/
    suspend fun insertDetailSatellites(satelliteDetailItem: SatelliteDetailItem) =
        local.insertDetailSatellites(satelliteDetailItem)

    suspend fun insertSatellitesItem(satelliteItem: SatelliteItem) =
        local.insertSatellitesItem(satelliteItem)

    fun getSatellitesDetailDatabase(id: Int) = local.getSatellitesDetailDatabase(id)

    fun searchSatelliteByName(name: String) = local.searchSatelliteByName(name)


}