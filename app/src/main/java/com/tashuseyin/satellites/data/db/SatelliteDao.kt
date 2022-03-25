package com.tashuseyin.satellites.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.tashuseyin.satellites.data.model.model_satellite.SatelliteItem
import com.tashuseyin.satellites.data.model.model_satellite_detail.SatelliteDetailItem

@Dao
interface SatelliteDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertSatellitesItem(satelliteItem: SatelliteItem)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertDetailSatellites(satelliteDetailItem: SatelliteDetailItem)

    @Query("SELECT * FROM satellites_detail_table WHERE id = :id")
    fun getSatellitesDetailDatabase(id: Int): LiveData<SatelliteDetailItem>

    @Query("SELECT * FROM satellites_table WHERE name LIKE :name")
    fun searchSatelliteByName(name: String): LiveData<List<SatelliteItem>>
}