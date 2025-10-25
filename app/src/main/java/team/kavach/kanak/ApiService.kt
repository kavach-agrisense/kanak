package team.kavach.kanak

import retrofit2.http.GET
import retrofit2.http.Query
import team.kavach.kanak.Weather.Forecast.ForecastInfo

interface ApiService {


    @GET("forecast.json")
    suspend fun fetchWeatherForecast(
        @Query("key")
        apiKey : String,

        @Query("q")
        location : String,

        @Query("days")
        days : Int
    ) : ForecastInfo

}