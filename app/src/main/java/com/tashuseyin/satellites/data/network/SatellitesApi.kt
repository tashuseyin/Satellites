package com.tashuseyin.satellites.data.network

import com.tashuseyin.satellites.data.model.model_satellite.Satellites
import com.tashuseyin.satellites.data.model.model_satellite_detail.SatelliteDetail
import com.tashuseyin.satellites.data.model.model_satellite_position.Result
import retrofit2.http.GET

interface SatellitesApi {
    @GET("f3715c6a468a8b98608177fbdcdf1059/raw/2e9911f5d4a1bf1891fd1bf374655995be159a79/satellite-list.json")
    suspend fun getSatellites(): Satellites

    @GET("a1a4585a3be1e903ea06870dae3605b9/raw/9c67578fecba91ecd1407362e039bb519f6e81f4/satellite-detail.json")
    suspend fun getSatelliteById(): SatelliteDetail

    @GET("3d1307835c5a3dfb63126d007ee82a74/raw/22b03298f86663362755ed02cb29da376ed10a33/positions.json")
    suspend fun getSatellitePosition(): Result
}