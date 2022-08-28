package com.example.gameofthroneshouses.usecases

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.example.gameofthroneshouses.models.House
import com.example.gameofthroneshouses.datasources.HouseRepository
import com.example.gameofthroneshouses.utils.SResult
import com.example.gameofthroneshouses.utils.loading

class HouseUseCase(private val repository: HouseRepository) {

    suspend fun localHousesData() = repository.getLocalHouses()

    private val remoteHousesLiveData: LiveData<SResult<List<House>>> by lazy {
        liveData {
            emit(loading())
            emit(repository.getRemoteHouses())
        }
    }

   suspend  fun getHouseByName(name: String ) = repository.getHouseByName(name)

}