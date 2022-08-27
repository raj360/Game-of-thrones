package com.example.gameofthroneshouses.viewmodels

import androidx.lifecycle.*
import com.example.gameofthroneshouses.models.House
import com.example.gameofthroneshouses.network.result.SResult
import com.example.gameofthroneshouses.network.result.loading
import com.example.gameofthroneshouses.source.HouseRepository
import kotlinx.coroutines.Dispatchers

class HouseViewModel(
    private val repository: HouseRepository
) : ViewModel() {

    private val remoteHousesLiveData: LiveData<SResult<List<House>>> by lazy {
        liveData(viewModelScope.coroutineContext + Dispatchers.IO) {
            emit(loading())
            emit(repository.getRemoteHouses())
        }
    }

    fun getHousesList() =  remoteHousesLiveData

}