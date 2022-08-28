package com.example.gameofthroneshouses

import android.app.Application
import androidx.room.Room
import com.example.gameofthroneshouses.di.*
import com.example.gameofthroneshouses.providers.database.Database
import com.example.gameofthroneshouses.utils.DB_NAME
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
        bind() from singleton {
            Room.databaseBuilder(applicationContext, Database::class.java, DB_NAME)
                .allowMainThreadQueries()
                .build()
        }
        import(networkModule, allowOverride = true)
        import(databaseModule, allowOverride = true)
        import(localDataSourceModule, allowOverride = true)
        import(remoteDataSourceModule, allowOverride = true)
        import(repositoryModule, allowOverride = true)
        import(useCasesModule, allowOverride = true)
        import(viewModelsModule, allowOverride = true)
    }


}
