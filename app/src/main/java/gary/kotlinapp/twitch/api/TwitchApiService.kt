package gary.kotlinapp.twitch.api

import gary.kotlinapp.twitch.model.TwitchChannels
import io.reactivex.Single

interface TwitchApiService {

    /**
     * Search for channels
     *
     * @param query The query
     * @param page The page (for paging purposes)
     *
     * @return A Single Observable which emits TwitchChannels object
     */
    fun searchChannels(
        query: String,
        page: Int
    ): Single<TwitchChannels>

}