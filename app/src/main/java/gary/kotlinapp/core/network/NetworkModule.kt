package gary.kotlinapp.core.network

import android.content.Context
import com.facebook.stetho.okhttp3.StethoInterceptor
import com.jakewharton.byteunits.DecimalByteUnit
import dagger.Module
import dagger.Provides
import gary.kotlinapp.core.application.ApplicationContext
import okhttp3.Cache
import okhttp3.OkHttpClient
import java.io.File
import javax.inject.Singleton

@Module
class NetworkModule {

    private val DISK_CACHE_SIZE = DecimalByteUnit.MEGABYTES.toBytes(50)

    @Provides
    @Singleton
    fun okHttpClientBuilder(@ApplicationContext context: Context): OkHttpClient.Builder {
        val cacheDir = File(context.cacheDir, "http")
        val cache = Cache(cacheDir, DISK_CACHE_SIZE)

        return OkHttpClient().newBuilder().cache(cache).addNetworkInterceptor(StethoInterceptor())
    }

}