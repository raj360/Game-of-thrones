package com.example.gameofthroneshouses.providers.network

import android.annotation.SuppressLint
import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.security.SecureRandom
import java.security.cert.CertificateException
import java.security.cert.X509Certificate
import java.util.concurrent.TimeUnit
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager

private val SERVER_ERROR_CODES = listOf(404, 504, 400, 401, 500, 403)

///-------------- NETWORK CONSTANTS ----////
const val NETWORK_AVAILABLE_AGE = 20
const val REQUEST_TIME_OUT = 20L

fun getUnsafeOkHttpClient(): OkHttpClient.Builder {
    try {
        // Create a trust manager that does not validate certificate chains
        return OkHttpClient.Builder().apply {
            val trustAllCerts = arrayOf<TrustManager>(object : X509TrustManager {
                override fun getAcceptedIssuers(): Array<X509Certificate> = arrayOf()

                @SuppressLint("TrustAllX509TrustManager")
                @Throws(CertificateException::class)
                override fun checkClientTrusted(chain: Array<X509Certificate>, authType: String) {
                }

                @SuppressLint("TrustAllX509TrustManager")
                @Throws(CertificateException::class)
                override fun checkServerTrusted(chain: Array<X509Certificate>, authType: String) {
                }
            })

            val sslContext = SSLContext.getInstance("SSL").apply {
                init(null, trustAllCerts, SecureRandom())
            }
            sslSocketFactory(sslContext.socketFactory, trustAllCerts[0] as X509TrustManager)

        }
    } catch (e: Exception) {
        throw RuntimeException(e)
    }
}

fun createOkHttpClient(): OkHttpClient {
    val httpClient = getUnsafeOkHttpClient()
    httpClient.connectTimeout(REQUEST_TIME_OUT, TimeUnit.SECONDS)
    httpClient.writeTimeout(REQUEST_TIME_OUT, TimeUnit.SECONDS)
    httpClient.readTimeout(REQUEST_TIME_OUT, TimeUnit.SECONDS)


    httpClient.addInterceptor { chain ->
        val request = chain.request()
        val originalHttpUrl = request.url()

        val url = originalHttpUrl.newBuilder().build().url()

        chain.proceed(
            request.newBuilder()
                .url(url)
                .addHeader("Content-type", "application/json")
                .addHeader("Cache-Control", "public, max-age=$NETWORK_AVAILABLE_AGE")
                .build()
        )
    }

    httpClient.addNetworkInterceptor { chain ->
        val originalResponse = chain.proceed(chain.request())
        return@addNetworkInterceptor if (SERVER_ERROR_CODES.contains(originalResponse.code())) {
            return@addNetworkInterceptor originalResponse.newBuilder().code(200).body(
                originalResponse.body()
            ).build()
        } else originalResponse
    }
    return httpClient.build()
}

inline fun <reified F> createWebServiceApi(okHttpClient: OkHttpClient, url: String): F {
    val moshi = Moshi.Builder().build()
    val retrofit = Retrofit.Builder()
        .baseUrl(url)
        .client(okHttpClient)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()
    return retrofit.create(F::class.java)
}