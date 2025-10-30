package team.kavach.kanak

import retrofit2.http.GET
import retrofit2.http.Query
import team.kavach.kanak.Weather.CurrentModel.WeatherInfo
import team.kavach.kanak.Weather.Forecast.ForecastInfo
import team.kavach.kanak.Weather.HourlyModel.HourlyForecast

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

    @GET("v1/currentConditions:lookup")
    suspend fun fetchCurrentWeather(
        @Query("key")
        apiKey : String,

        @Query("location.latitude")
        latitude : Double,

        @Query("location.longitude")
        longitude : Double,

        @Query("fields")
        fields : String
    ) : WeatherInfo


    @GET("v1/forecast/hours:lookup")
    suspend fun fetchHourlyForecast(
        @Query("key")
        apiKey : String,

        @Query("location.latitude")
        latitude: Double,

        @Query("location.longitude")
        longitude : Double
    ) : HourlyForecast
}
