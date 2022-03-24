package com.tashuseyin.satellites.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.tashuseyin.satellites.data.model.model_satellite_detail.SatelliteDetailItem

@Database(entities = [SatelliteDetailItem::class], version = 1, exportSchema = false)
abstract class SatelliteDatabase : RoomDatabase() {
    abstract fun satelliteDao(): SatelliteDao

}