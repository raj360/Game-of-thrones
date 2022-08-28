package com.example.gameofthroneshouses.datasources.local

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.example.gameofthroneshouses.models.House
import com.example.gameofthroneshouses.providers.database.dao.IHouseDao
import com.example.gameofthroneshouses.utils.SResult
import com.example.gameofthroneshouses.utils.emptyResult
import com.example.gameofthroneshouses.utils.successResult

class LocalDataSource(private val houseDao: IHouseDao) : ILocalDataSource {
    override suspend fun getAll(): SResult.Success<List<House>>  =
        successResult(houseDao.getAllHouses())

    override suspend fun getByName(name: String): LiveData<SResult<House>>  =
        houseDao.getByName(name).map{
        if(it.name.isNotBlank()) successResult(it)
        else emptyResult()
    }

    override suspend fun insertAll(data: List<House>) = houseDao.insertAll(data)
}