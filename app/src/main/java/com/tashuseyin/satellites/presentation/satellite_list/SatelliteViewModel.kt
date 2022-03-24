package com.tashuseyin.satellites.presentation.satellite_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tashuseyin.satellites.data.model.SatelliteItem
import com.tashuseyin.satellites.data.repository.SatellitesRepository
import com.tashuseyin.satellites.presentation.satellite_list.state.SatelliteListState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class SatelliteViewModel @Inject constructor(
    private val repository: SatellitesRepository
) : ViewModel() {

    private val _state = MutableStateFlow(SatelliteListState())
    val state: StateFlow<SatelliteListState> = _state

    init {
        viewModelScope.launch {
            getSatellites()
        }
    }

    private suspend fun getSatellites() {
        _state.value = SatelliteListState(isLoading = true)
        try {
            val response = repository.getSatellites()
            response.forEach {
                insertSatellites(it)
            }
            _state.value =
                SatelliteListState(satelliteList = response)
        } catch (e: HttpException) {
            _state.value =
                SatelliteListState(error = e.localizedMessage ?: "An unexpected error occurred")
        } catch (e: IOException) {
            _state.value =
                SatelliteListState(error = "Couldn't reach server. Check your internet connection.")
        }
    }

    private suspend fun insertSatellites(satelliteItem: SatelliteItem) {
        repository.insertSatellite(satelliteItem)
    }

}
