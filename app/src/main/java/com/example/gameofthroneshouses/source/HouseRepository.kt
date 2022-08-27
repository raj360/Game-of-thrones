package com.example.gameofthroneshouses.source

import com.example.gameofthroneshouses.source.remote.RemoteDataSource
import com.example.gameofthroneshouses.network.result.mapListTo

class HouseRepository(private val remoteDataSource: RemoteDataSource) {

    suspend fun getRemoteHouses()  = remoteDataSource.getAllHouses().mapListTo()

}