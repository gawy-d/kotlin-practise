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
import gary.kotlinapp.twitch.api.TwitchApiManager
import gary.kotlinapp.twitch.mapper.TwitchChannelsDtoTwitchChannelsMapper
import gary.kotlinapp.twitch.repository.TwitchChannelsRepository
import io.reactivex.Scheduler
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class TwitchModule {

    @Provides
    @Singleton
    @TwitchApiEndpoint
    fun twitchApiUrl(@ApplicationContext context: Context): HttpUrl {
        return HttpUrl.parse(context.getString(R.string.twitch_api_endpoint))!!
    }

    @Provides
    @Singleton
    @TwitchApiClientId
    fun twitchApiClientId(@ApplicationContext context: Context): String {
        return context.getString(R.string.twitch_client_id)
    }

    @Provides
    @Singleton
    @TwitchApiClientSecret
    fun twitchClientSecret(@ApplicationContext context: Context): String {
        return context.getString(R.string.twitch_client_secret)
    }

    @Provides
    @Singleton
    @TwitchApiInterceptor
    fun twitchApiHeaderInterceptor(@TwitchApiClientId clientId: String): Interceptor {
        return TwitchApiHeaderInterceptor(clientId)
    }

    @Provides
    @Singleton
    @TwitchApiOkHttpClient
    fun twitchApiOkHttpClient(okHttpClientBuilder: OkHttpClient.Builder,
        @TwitchApiInterceptor interceptor: Interceptor): OkHttpClient {
        return okHttpClientBuilder.addInterceptor(interceptor).build()
    }

    @Provides
    @Singleton
    @TwitchApiRetrofitBuilder
    fun twitchApiRetrofitBuilder(@TwitchApiOkHttpClient client: OkHttpClient,
        @NetworkScheduler scheduler: Scheduler, gson: Gson): Retrofit.Builder {
        return Retrofit.Builder().client(client)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(scheduler))
            .addConverterFactory(GsonConverterFactory.create(gson))
    }

    @Provides
    @Singleton
    @TwitchApiRetrofit
    fun twitchApiRetrofit(@TwitchApiRetrofitBuilder retrofitBuilder: Retrofit.Builder,
        @TwitchApiEndpoint httpUrl: HttpUrl): Retrofit {
        return retrofitBuilder.baseUrl(httpUrl).build()
    }

    @Provides
    @Singleton
    fun twitchApi(@TwitchApiRetrofit retrofit: Retrofit): TwitchApi {
        return retrofit.create(TwitchApi::class.java)
    }

    @Provides
    @Singleton
    fun twitchChannelsRepository(twitchApi: TwitchApi): TwitchChannelsRepository {
        return TwitchChannelsRepository(twitchApi)
    }

    @Provides
    @Singleton
    fun twitchChannelsDtoTwitchChannelsMapper(): TwitchChannelsDtoTwitchChannelsMapper {
        return TwitchChannelsDtoTwitchChannelsMapper()
    }

    @Provides
    @Singleton
    fun twitchApiManager(channelsRepository: TwitchChannelsRepository,
        channelsMapper: TwitchChannelsDtoTwitchChannelsMapper): TwitchApiManager {
        return TwitchApiManager(channelsRepository, channelsMapper)
    }

}