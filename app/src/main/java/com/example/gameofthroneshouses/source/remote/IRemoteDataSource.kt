package com.example.gameofthroneshouses.source.remote

import com.example.gameofthroneshouses.models.House
import com.example.gameofthroneshouses.network.result.SResult

interface IRemoteDataSource {
    suspend fun getAllHouses(): SResult<List<House>>
}