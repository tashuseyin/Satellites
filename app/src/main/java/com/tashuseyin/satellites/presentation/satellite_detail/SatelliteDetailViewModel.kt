package com.tashuseyin.satellites.presentation.satellite_detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tashuseyin.satellites.common.Constants
import com.tashuseyin.satellites.data.model.model_satellite.SatelliteItem
import com.tashuseyin.satellites.data.model.model_satellite_detail.SatelliteDetailItem
import com.tashuseyin.satellites.data.repository.SatellitesRepository
import com.tashuseyin.satellites.presentation.satellite_detail.state.PositionState
import com.tashuseyin.satellites.presentation.satellite_detail.state.SatelliteDetailState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class SatelliteDetailViewModel @Inject constructor(
    private var repository: SatellitesRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    val satelliteItem = savedStateHandle.get<SatelliteItem>(Constants.SATELLITES_ITEM)

    private val _state = MutableStateFlow(SatelliteDetailState())
    val state: StateFlow<SatelliteDetailState> = _state

    private val _positionState = MutableStateFlow(PositionState())
    val positionState: StateFlow<PositionState> = _positionState

    val readSatelliteDetailDatabase: LiveData<SatelliteDetailItem>? = satelliteItem?.let {
        repository.getSatellitesDetailDatabase(
            it.id
        )
    }

    init {
        viewModelScope.launch {
            satelliteItem?.let {
                getSatellitePosition(it)
            }
        }
    }

    fun getSatelliteDetailItem() = viewModelScope.launch {
        satelliteItem?.let {
            getSatelliteById(it)
        }
    }

    private suspend fun getSatellitePosition(satelliteItem: SatelliteItem) {
        _positionState.value = PositionState(isLoading = true)
        try {
            val response =
                repository.getSatellitePosition().list.filter { satelliteItem.id.toString() == it.id }
            _positionState.value =
                PositionState(position = response)
        } catch (e: HttpException) {
            _positionState.value =
                PositionState(error = e.localizedMessage ?: "An unexpected error occurred")
        } catch (e: IOException) {
            _positionState.value =
                PositionState(error = "Couldn't reach server. Check your internet connection.")
        }
    }


    private suspend fun getSatelliteById(satelliteItem: SatelliteItem) {
        _state.value = SatelliteDetailState(isLoading = true)
        try {
            val response =
                repository.getSatelliteById().filter { satelliteItem.id == it.id }
            insertSatelliteDetailItem(response.first())
            _state.value =
                SatelliteDetailState(satellite = response.first())
        } catch (e: HttpException) {
            _state.value =
                SatelliteDetailState(error = e.localizedMessage ?: "An unexpected error occurred")
        } catch (e: IOException) {
            _state.value =
                SatelliteDetailState(error = "Couldn't reach server. Check your internet connection.")
        }
    }

    private suspend fun insertSatelliteDetailItem(satelliteDetailItem: SatelliteDetailItem) {
        repository.insertDetailSatellites(satelliteDetailItem)
    }
}
