package com.example.gameofthroneshouses.usecases

import androidx.lifecycle.liveData
import com.example.gameofthroneshouses.datasources.HouseRepository
import com.example.gameofthroneshouses.utils.loading
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class HouseUseCase(private val repository: HouseRepository) {

    suspend fun localHousesData() = repository.getLocalHouses()

    suspend fun remoteHousesLiveData() = liveData {
        emit(loading())
        emit(repository.getRemoteHouses())
    }
    suspend  fun getHouseByName(name: String ) =  repository.getHouseByName(name)


}