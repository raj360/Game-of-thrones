package com.example.gameofthroneshouses.di

import com.example.gameofthroneshouses.viewmodels.HouseViewModel
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.instance
import org.kodein.di.provider

val viewModelsModule = DI.Module("view_models_module") {
    bind() from provider { HouseViewModel(instance()) }
}