package com.example.myfinalproject.api

import com.example.myfinalproject.order.Team
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

//website for api documentation:
// https://sportsdata.io/developers#coverage

val apiKey = "050f76fd2b784f7db88f162dd4932831"

const val base_url = "https://api.sportsdata.io/v3/nfl/scores/json/Standings/2023/"
const val api = "https://api.sportsdata.io/v3/nfl/scores/json/Standings/2023?key=050f76fd2b784f7db88f162dd4932831"




//defining api service
interface ApiService {
    @GET(api) //api endpoint
    suspend fun getTeams(@Query("key") apiKey: String): List<Team>

}

//create Retrofit Instance
object RetrofitClient {
    val apiService: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl(base_url)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)

    }
}


