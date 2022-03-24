package com.tashuseyin.satellites.data.network

import com.tashuseyin.satellites.data.model.model_satellite.Satellites
import retrofit2.http.GET

interface SatellitesApi {
    @GET("f3715c6a468a8b98608177fbdcdf1059/raw/2e9911f5d4a1bf1891fd1bf374655995be159a79/satellite-list.json")
    suspend fun getSatellites(): Satellites
}