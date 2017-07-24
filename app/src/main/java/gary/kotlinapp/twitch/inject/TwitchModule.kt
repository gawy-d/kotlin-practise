package gary.kotlinapp.twitch.inject

import android.content.Context
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import gary.kotlinapp.R
import gary.kotlinapp.core.application.ApplicationContext
import gary.kotlinapp.core.scheduler.NetworkScheduler
import gary.kotlinapp.twitch.api.TwitchApi
import gary.kotlinapp.twitch.api.TwitchApiHeaderInterceptor
import gary.kotlinapp.twitch.api.TwitchApiService
import gary.kotlinapp.twitch.mapper.TwitchChannelsDtoTwitchChannelsMapper
import gary.kotlinapp.twitch.repository.TwitchChannelsRepository
import io.reactivex.Scheduler
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
class TwitchModule {

    @Provides
    @TwitchScope
    @TwitchApiEndpoint
    fun twitchApiUrl(@ApplicationContext context: Context): HttpUrl =
        HttpUrl.parse(context.getString(R.string.twitch_api_endpoint))!!

    @Provides
    @TwitchScope
    @TwitchApiClientId
    fun twitchApiClientId(@ApplicationContext context: Context): String =
        context.getString(R.string.twitch_client_id)

    @Provides
    @TwitchScope
    @TwitchApiClientSecret
    fun twitchClientSecret(@ApplicationContext context: Context): String =
        context.getString(R.string.twitch_client_secret)

    @Provides
    @TwitchScope
    @TwitchApiInterceptor
    fun twitchApiHeaderInterceptor(@TwitchApiClientId clientId: String): Interceptor =
        TwitchApiHeaderInterceptor(clientId)

    @Provides
    @TwitchScope
    @TwitchApiOkHttpClient
    fun twitchApiOkHttpClient(
        okHttpClientBuilder: OkHttpClient.Builder,
        @TwitchApiInterceptor interceptor: Interceptor
    ): OkHttpClient =
        okHttpClientBuilder.addInterceptor(interceptor).build()

    @Provides
    @TwitchScope
    @TwitchApiRetrofitBuilder
    fun twitchApiRetrofitBuilder(
        @TwitchApiOkHttpClient client: OkHttpClient,
        @NetworkScheduler scheduler: Scheduler,
        gson: Gson
    ): Retrofit.Builder =
        Retrofit.Builder().client(client)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(scheduler))
            .addConverterFactory(GsonConverterFactory.create(gson))

    @Provides
    @TwitchScope
    @TwitchApiRetrofit
    fun twitchApiRetrofit(
        @TwitchApiRetrofitBuilder retrofitBuilder: Retrofit.Builder,
        @TwitchApiEndpoint httpUrl: HttpUrl
    ): Retrofit =
        retrofitBuilder.baseUrl(httpUrl).build()

    @Provides
    @TwitchScope
    fun twitchApi(@TwitchApiRetrofit retrofit: Retrofit): TwitchApi =
        retrofit.create(TwitchApi::class.java)

    @Provides
    @TwitchScope
    fun twitchChannelsRepository(twitchApi: TwitchApi): TwitchChannelsRepository =
        TwitchChannelsRepository(twitchApi)

    @Provides
    @TwitchScope
    fun twitchChannelsDtoTwitchChannelsMapper(): TwitchChannelsDtoTwitchChannelsMapper =
        TwitchChannelsDtoTwitchChannelsMapper()

    @Provides
    @TwitchScope
    fun twitchApiService(
        channelsRepository: TwitchChannelsRepository,
        channelsMapper: TwitchChannelsDtoTwitchChannelsMapper
    ): TwitchApiService =
        TwitchApiService(channelsRepository, channelsMapper)

}