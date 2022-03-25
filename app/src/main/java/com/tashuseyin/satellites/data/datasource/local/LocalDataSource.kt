package com.tashuseyin.satellites.data.datasource.local

import androidx.lifecycle.LiveData
import com.tashuseyin.satellites.data.db.SatelliteDatabase
import com.tashuseyin.satellites.data.model.model_satellite.SatelliteItem
import com.tashuseyin.satellites.data.model.model_satellite_detail.SatelliteDetailItem
import javax.inject.Inject

class LocalDataSource @Inject constructor(
    private val database: SatelliteDatabase
) : LocalDataSourceImpl {

    override suspend fun insertDetailSatellites(satelliteDetailItem: SatelliteDetailItem) {
        database.satelliteDao().insertDetailSatellites(satelliteDetailItem)
    }

    override suspend fun insertSatellitesItem(satelliteItem: SatelliteItem) {
        database.satelliteDao().insertSatellitesItem(satelliteItem)
    }

    override fun getSatellitesDetailDatabase(id: Int): LiveData<SatelliteDetailItem> {
        return database.satelliteDao().getSatellitesDetailDatabase(id)
    }

    override fun searchSatelliteByName(name: String): LiveData<List<SatelliteItem>> {
        return database.satelliteDao().searchSatelliteByName(name)
    }
}
