package com.example.gameofthroneshouses.di

import com.example.gameofthroneshouses.providers.database.Database
import com.example.gameofthroneshouses.providers.database.dao.IHouseDao
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.instance
import org.kodein.di.provider


val databaseModule = DI.Module("database_module") {
    bind<IHouseDao>() with provider { instance<Database>().getAllHouseDao() }
}