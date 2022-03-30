package com.tashuseyin.satellites.presentation.satellite_detail

import androidx.lifecycle.*
import com.tashuseyin.satellites.common.Constants
import com.tashuseyin.satellites.data.model.model_satellite.SatelliteItem
import com.tashuseyin.satellites.data.model.model_satellite_detail.SatelliteDetailItem
import com.tashuseyin.satellites.data.model.model_satellite_position.SatelliteItemPosition
import com.tashuseyin.satellites.data.repository.SatellitesRepository
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
    private val _position: MutableLiveData<SatelliteItemPosition> = MutableLiveData()
    val position get() = _position

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

    private suspend fun insertSatelliteDetailItem(satelliteDetailItem: SatelliteDetailItem) {
        repository.insertDetailSatellites(satelliteDetailItem)
    }

    private suspend fun getSatellitePosition(satelliteItem: SatelliteItem) {
        _position.value =
            repository.getSatellitePosition().list.first { satelliteItem.id.toString() == it.id }
    }

    private suspend fun getSatelliteById(satelliteItem: SatelliteItem) {
        _state.value = SatelliteDetailState(isLoading = true)
        try {
            val response =
                repository.getSatelliteById().first { satelliteItem.id == it.id }
            insertSatelliteDetailItem(response)
            _state.value =
                SatelliteDetailState(satellite = response)
        } catch (e: HttpException) {
            _state.value =
                SatelliteDetailState(error = e.localizedMessage ?: "An unexpected error occurred")
        } catch (e: IOException) {
            _state.value =
                SatelliteDetailState(error = "Couldn't reach server. Check your internet connection.")
        }
    }
}
