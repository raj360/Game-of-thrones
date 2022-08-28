package com.example.gameofthroneshouses.datasources.remote

import com.example.gameofthroneshouses.providers.network.api.IHouseApi
import com.example.gameofthroneshouses.utils.emptyResult
import com.example.gameofthroneshouses.utils.errorResult
import com.example.gameofthroneshouses.utils.log
import com.example.gameofthroneshouses.utils.successResult
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