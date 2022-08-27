package com.pharmacure.online_pharmacy_app.viewmodels.factory

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.gameofthroneshouses.Application
import org.kodein.di.*
import org.kodein.type.erased


class ViewModelFactory(appContext: Context) : ViewModelProvider.Factory, DIAware {
    override val di: DI = (appContext as Application).di

    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        di.direct.Instance(erased(modelClass))

}





