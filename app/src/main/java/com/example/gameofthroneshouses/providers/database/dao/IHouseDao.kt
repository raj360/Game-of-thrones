package com.example.gameofthroneshouses.providers.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.example.gameofthroneshouses.models.House
import com.example.gameofthroneshouses.providers.database.dao.base.IBaseDao

@Dao
interface IHouseDao : IBaseDao<House> {
    @Query("SELECT * FROM House WHERE name=:name LIMIT 1")
    fun getByName(name: String): LiveData<House>

    @Query("SELECT * FROM  House LIMIT 20")
    fun getAllHouses(): List<House>
}