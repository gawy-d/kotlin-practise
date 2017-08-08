package gary.kotlinapp.twitch.api

import gary.kotlinapp.twitch.api.TwitchApiConstants.HEADER_KEY_ACCEPT
import gary.kotlinapp.twitch.api.TwitchApiConstants.HEADER_KEY_CLIENT_ID
import gary.kotlinapp.twitch.api.TwitchApiConstants.HEADER_VALUE_ACCEPT
import okhttp3.Interceptor
import okhttp3.Response

class TwitchApiHeaderInterceptor(
    private val clientId: String
) : Interceptor {

    override fun intercept(
        chain: Interceptor.Chain
    ): Response {
        val builder = chain.request().newBuilder()

        builder.addHeader(HEADER_KEY_CLIENT_ID, clientId).addHeader(HEADER_KEY_ACCEPT, HEADER_VALUE_ACCEPT)

        return chain.proceed(builder.build())
    }
}