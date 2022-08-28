package com.example.gameofthroneshouses.providers.database.dao.base

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE


interface IBaseDao<T> {

    @Insert(onConflict = REPLACE)
    fun insertAll(list: List<T>)

    @Delete
    fun delete(data: T)

}