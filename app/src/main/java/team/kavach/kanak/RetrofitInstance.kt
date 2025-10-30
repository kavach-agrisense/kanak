package team.kavach.kanak

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    private fun getInstance() : Retrofit {
        return Retrofit.Builder().baseUrl("https://weather.googleapis.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun getApiService() : ApiService {
        return getInstance().create(ApiService::class.java)
    }
}