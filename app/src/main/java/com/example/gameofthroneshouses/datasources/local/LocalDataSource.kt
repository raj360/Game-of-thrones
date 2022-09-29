package com.example.gameofthroneshouses.datasources.local

import androidx.lifecycle.map
import com.example.gameofthroneshouses.models.House
import com.example.gameofthroneshouses.providers.database.dao.IHouseDao
import com.example.gameofthroneshouses.utils.emptyResult
import com.example.gameofthroneshouses.utils.successResult



class LocalDataSource(private val houseDao: IHouseDao) : ILocalDataSource {
    override suspend fun getAll() = successResult(houseDao.getAllHouses())

    override suspend fun getByName(name: String)  =
        houseDao.getByName(name).map{ houseDetails ->
        if(houseDetails.name.isNotBlank()) successResult(houseDetails)
        else emptyResult()
    }

    override suspend fun insertAll(data: List<House>) = houseDao.insertAll(data)
}
