package com.example.gameofthroneshouses.providers.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.gameofthroneshouses.models.House
import com.example.gameofthroneshouses.providers.database.dao.IHouseDao

/**
 * Set up for local cache db to avoid making another call for details view
 */
@Database(entities = [House::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class Database : RoomDatabase() {
    abstract fun getAllHouseDao(): IHouseDao
}

