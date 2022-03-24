package com.tashuseyin.satellites.data.datasource.local

import com.tashuseyin.satellites.data.db.SatelliteDatabase
import com.tashuseyin.satellites.data.model.model_satellite_detail.SatelliteDetailItem
import javax.inject.Inject

class LocalDataSource @Inject constructor(
    private val database: SatelliteDatabase
) : LocalDataSourceImpl {

    override suspend fun insertSatellite(satelliteDetailItem: SatelliteDetailItem) {
        database.satelliteDao().insertSatellites(satelliteDetailItem)
    }
}
