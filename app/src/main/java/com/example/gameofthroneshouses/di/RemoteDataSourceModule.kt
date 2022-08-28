package com.example.gameofthroneshouses.di


import com.example.gameofthroneshouses.datasources.remote.RemoteDataSource
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.instance
import org.kodein.di.singleton

val remoteDataSourceModule = DI.Module("remote_data_source_module") {
    bind() from singleton { RemoteDataSource(instance()) }
}