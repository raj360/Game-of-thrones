package com.example.gameofthroneshouses.source.remote

import com.example.gameofthroneshouses.network.api.IHouseApi
import com.example.gameofthroneshouses.network.result.emptyResult
import com.example.gameofthroneshouses.network.result.errorResult
import com.example.gameofthroneshouses.network.result.successResult
import com.example.gameofthroneshouses.utils.log
import ru.gildor.coroutines.retrofit.Result
import ru.gildor.coroutines.retrofit.awaitResult

class RemoteDataSource (private var houseApi: IHouseApi ) : IRemoteDataSource {

    override suspend fun getAllHouses() =
        when (val result = houseApi.getAllHouses().awaitResult()) {
            is Result.Ok -> {
                successResult(result.value)
            }
            is Result.Error -> {
                errorResult(result.response.code(), result.exception.message())
            }
            is Result.Exception -> {
                log { "Exception occurred => ${result.exception}" }
                emptyResult()
            }
        }
}