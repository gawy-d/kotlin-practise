package gary.kotlinapp.core.parsing

import com.google.gson.Gson

interface ParsingProvider {

    fun gson(): Gson

}