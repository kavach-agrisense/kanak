package team.kavach.kanak.Weather


interface WeatherDao {


    suspend fun upsertWeatherInfo (weatherInfo : WeatherInfo)


    suspend fun deleteWeatherInfo(weatherInfo : WeatherInfo)
}