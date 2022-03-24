package com.tashuseyin.satellites.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.tashuseyin.satellites.data.model.model_satellite.SatelliteItem

@Dao
interface SatelliteDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertSatellites(satelliteItem: SatelliteItem)
}