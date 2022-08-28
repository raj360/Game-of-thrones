package com.example.gameofthroneshouses.di

import com.example.gameofthroneshouses.usecases.HouseUseCase
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.instance
import org.kodein.di.provider

val useCasesModule = DI.Module("use_cases_module") {
    bind() from provider { HouseUseCase(instance()) }
}