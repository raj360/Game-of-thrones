package com.example.gameofthroneshouses.datasources.local

import androidx.lifecycle.LiveData
import com.example.gameofthroneshouses.models.House
import com.example.gameofthroneshouses.utils.SResult

interface ILocalDataSource {

    suspend fun getAll(): SResult.Success<List<House>>

    suspend fun getByName(name: String): LiveData<SResult<House>>

    suspend fun insertAll(data: List<House>)

}