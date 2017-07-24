package gary.kotlinapp.twitch.api

import gary.kotlinapp.twitch.api.TwitchApiConstants.SEARCH_CHANNELS
import gary.kotlinapp.twitch.api.dto.TwitchChannelsDto
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface TwitchApi {

    @GET(SEARCH_CHANNELS)
    fun searchChannels(
        @Query("query") query: String,
        @Query("offset") page: Int,
        @Query("limit") limit: Int = 20
    ): Single<TwitchChannelsDto>

}