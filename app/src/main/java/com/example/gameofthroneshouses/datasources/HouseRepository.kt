package com.example.gameofthroneshouses.datasources

import com.example.gameofthroneshouses.datasources.local.LocalDataSource
import com.example.gameofthroneshouses.datasources.remote.RemoteDataSource
import com.example.gameofthroneshouses.utils.*

class HouseRepository(
    private val remoteDataSource: RemoteDataSource,
    private  val localDataSource: LocalDataSource
    ) {

    suspend fun getLocalHouses()  = localDataSource.getAll().apply {
        this.data.ifEmpty { emptyResult() }
    }

    suspend fun getHouseByName(name: String) = localDataSource.getByName(name)

    suspend fun getRemoteHouses()  = remoteDataSource.getAllHouses().also {
        if(it is SResult.Success && it.data.isNotEmpty()) localDataSource.insertAll(it.data)
    }.mapListTo()

}