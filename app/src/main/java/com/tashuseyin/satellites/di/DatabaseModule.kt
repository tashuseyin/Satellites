package com.tashuseyin.satellites.di

import android.content.Context
import androidx.room.Room
import com.tashuseyin.satellites.common.Constants
import com.tashuseyin.satellites.data.db.SatelliteDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context) =
        Room.databaseBuilder(
            context,
            SatelliteDatabase::class.java,
            Constants.SATELLITES_DETAIL_DATABASE_NAME
        ).build()

    @Provides
    @Singleton
    fun provideDao(database: SatelliteDatabase) = database.satelliteDao()
}