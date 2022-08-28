package com.example.gameofthroneshouses.di

import com.example.gameofthroneshouses.datasources.HouseRepository
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.instance
import org.kodein.di.singleton

val repositoryModule = DI.Module("repository_module") {
    bind() from singleton { HouseRepository(instance(), instance()) }
}