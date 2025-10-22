package team.kavach.kanak

import retrofit2.http.GET
import retrofit2.http.Query
import team.kavach.kanak.Weather.WeatherInfo

interface ApiService {

    @GET("current.json")
    suspend fun getWeatherInfo(
        @Query("key") apiKey : String,
        @Query("q") location : String
    ) : WeatherInfo

}