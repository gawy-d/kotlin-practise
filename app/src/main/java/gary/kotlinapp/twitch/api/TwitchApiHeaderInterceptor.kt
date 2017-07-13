package gary.kotlinapp.twitch.api

import okhttp3.Interceptor
import okhttp3.Response

class TwitchApiHeaderInterceptor(
    private val clientId: String
) : Interceptor {

    private val headerClientIdKey = "Client-ID"
    private val headerAcceptKey = "Accept"
    private val headerAcceptValue = "application/vnd.twitchtv.v5+json"

    override fun intercept(chain: Interceptor.Chain): Response {
        val builder = chain.request().newBuilder()

        builder.addHeader(headerClientIdKey, clientId).addHeader(headerAcceptKey, headerAcceptValue)

        return chain.proceed(builder.build())
    }
}