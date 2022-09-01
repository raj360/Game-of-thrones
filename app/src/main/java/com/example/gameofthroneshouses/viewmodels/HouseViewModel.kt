package com.example.gameofthroneshouses.viewmodels

import androidx.lifecycle.*
import com.example.gameofthroneshouses.models.House
import com.example.gameofthroneshouses.usecases.HouseUseCase
import com.example.gameofthroneshouses.utils.SResult
import com.example.gameofthroneshouses.utils.loading
import kotlinx.coroutines.Dispatchers

class HouseViewModel(
    private val houseUseCase: HouseUseCase
) : ViewModel() {

    private var name: MutableLiveData<String> = MutableLiveData()

    private val housesLiveData: LiveData<SResult<List<House>>> by lazy {
        liveData(viewModelScope.coroutineContext + Dispatchers.IO) {
            emit(loading())
            emit(houseUseCase.localHousesData())
            emitSource(houseUseCase.remoteHousesLiveData())
        }
    }

    private val houseLiveData: LiveData<SResult<House>> by lazy {
        liveData(viewModelScope.coroutineContext + Dispatchers.IO) {
            emit(loading())
            emitSource(houseUseCase.getHouseByName(name.value!!))
        }
    }

    fun getHousesList() =  housesLiveData

    fun getHouseDetails() = houseLiveData

    fun setHouseName(houseName: String) {
        name.value = houseName
    }

    /**
     * We do no have business logic to test
     * created a test scenario
     */
    fun isNameParamEmpty() = name.value.isNullOrBlank()

}