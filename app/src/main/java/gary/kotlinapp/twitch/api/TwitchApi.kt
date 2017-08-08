package gary.kotlinapp.twitch.api

import gary.kotlinapp.twitch.api.TwitchApiConstants.SEARCH_CHANNELS
import gary.kotlinapp.twitch.api.dto.TwitchChannelsDto
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface TwitchApi {

    /**
     * Fetch channels for given query
     *
     * @param query The search query
     * @param page The page (for paging purposes)
     * @param limit The number of results per page
     *
     * @return A Single Observable which provides a TwitchChannelsDto object
     */
    @GET(SEARCH_CHANNELS)
    fun searchChannels(
        @Query("query") query: String,
        @Query("offset") page: Int,
        @Query("limit") limit: Int = 20
    ): Single<TwitchChannelsDto>

}