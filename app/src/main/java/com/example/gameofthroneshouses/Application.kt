package com.example.gameofthroneshouses

import android.app.Application
import com.example.gameofthroneshouses.di.networkModule
import com.example.gameofthroneshouses.di.remoteDataSourceModule
import com.example.gameofthroneshouses.di.repositoryModule
import com.example.gameofthroneshouses.di.viewModelsModule
import com.pharmacure.online_pharmacy_app.viewmodels.factory.ViewModelFactory
import org.kodein.di.DI
import org.kodein.di.DIAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.bind
import org.kodein.di.singleton

class Application : Application(), DIAware {

    override val di: DI by DI.lazy {
        import(androidXModule(this@Application))
        bind() from singleton { ViewModelFactory(applicationContext) }
        import(networkModule, allowOverride = true)
        import(remoteDataSourceModule, allowOverride = true)
        import(repositoryModule, allowOverride = true)
        import(viewModelsModule, allowOverride = true)
    }


}
