package com.tashuseyin.satellites.data.datasource.local

import com.tashuseyin.satellites.data.db.SatelliteDatabase
import com.tashuseyin.satellites.data.model.SatelliteItem
import javax.inject.Inject

class LocalDataSource @Inject constructor(
    private val database: SatelliteDatabase
) : LocalDataSourceImpl {

    override suspend fun insertSatellite(satelliteItem: SatelliteItem) {
        database.satelliteDao().insertSatellites(satelliteItem)
    }
}