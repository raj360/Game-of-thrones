package com.example.gameofthroneshouses.providers.network.api

import com.example.gameofthroneshouses.models.House
import retrofit2.Call
import retrofit2.http.*

interface IHouseApi {
    ///---------GET ALL HOUSES----------///
    @GET("api/houses")
    fun getAllHouses(): Call<List<House>>
}
