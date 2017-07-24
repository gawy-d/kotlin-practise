package gary.kotlinapp.core.network

import okhttp3.OkHttpClient

interface NetworkProvider {

    fun okHttpClientBuilder(): OkHttpClient.Builder

}