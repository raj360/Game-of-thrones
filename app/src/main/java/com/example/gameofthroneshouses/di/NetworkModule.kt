package com.example.gameofthroneshouses.di

import com.example.gameofthroneshouses.network.api.IHouseApi
import com.example.gameofthroneshouses.network.createOkHttpClient
import com.example.gameofthroneshouses.network.createWebServiceApi
import com.example.gameofthroneshouses.utils.SERVER_URL
import okhttp3.OkHttpClient
import org.kodein.di.*


private const val TAG_SERVER_URL = ""

val networkModule = DI.Module("network_module") {
    constant(TAG_SERVER_URL) with SERVER_URL
    bind<OkHttpClient>() with singleton { createOkHttpClient() }
    bind<IHouseApi>() with singleton { createWebServiceApi(instance(), instance(TAG_SERVER_URL)) }
}