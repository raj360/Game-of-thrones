package com.example.gameofthroneshouses.datasources.remote

import com.example.gameofthroneshouses.models.House
import com.example.gameofthroneshouses.utils.SResult

interface IRemoteDataSource {
    suspend fun getAllHouses(): SResult<List<House>>
}