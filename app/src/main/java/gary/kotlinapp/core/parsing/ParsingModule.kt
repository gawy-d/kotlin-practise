package gary.kotlinapp.core.parsing

import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ParsingModule {

    @Provides
    @Singleton
    fun gson(): Gson {
        return Gson()
    }

}