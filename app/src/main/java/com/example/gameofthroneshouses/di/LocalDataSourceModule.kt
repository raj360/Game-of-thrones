package com.example.gameofthroneshouses.di

import com.example.gameofthroneshouses.datasources.local.LocalDataSource
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.instance
import org.kodein.di.singleton

val localDataSourceModule = DI.Module("local_data_source") {
    bind() from singleton {LocalDataSource(instance())}
}