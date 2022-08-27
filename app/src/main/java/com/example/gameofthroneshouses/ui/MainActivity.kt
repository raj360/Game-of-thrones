package com.example.gameofthroneshouses.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.gameofthroneshouses.R
import com.example.gameofthroneshouses.models.House
import com.example.gameofthroneshouses.network.result.SResult
import com.example.gameofthroneshouses.utils.log
import com.example.gameofthroneshouses.viewmodels.HouseViewModel
import com.pharmacure.online_pharmacy_app.viewmodels.factory.ViewModelFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import org.kodein.di.DI
import org.kodein.di.DIAware
import org.kodein.di.android.di
import org.kodein.di.instance
import kotlin.coroutines.CoroutineContext

class MainActivity : AppCompatActivity(), CoroutineScope, DIAware {

    override val di: DI by di(this)
    private val factory: ViewModelFactory by instance()
    private lateinit var houseViewModel: HouseViewModel
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.IO

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        houseViewModel = ViewModelProvider(this, factory)[HouseViewModel::class.java]
        houseViewModel.getHousesList().observe(this, Observer(::handleHousesResult))
    }

    private fun handleHousesResult(result: SResult<List<House>>){
        when(result){
            is SResult.Loading -> {
                log { "Loading..." }
            }

            is SResult.Success -> {
                log { "We have response ${result.data}" }
            }

            is SResult.Error -> {
                log {"Error occurred ${result.message}"}
            }

            is SResult.Empty -> {
                log {"An Exception occurred"}
            }
        }
    }

}
