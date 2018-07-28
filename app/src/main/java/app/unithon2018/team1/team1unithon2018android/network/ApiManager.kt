package app.unithon2018.team1.team1unithon2018android.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiManager {

  private val URL = "http://52.79.230.255:5000/"

  private val retrofit =
      Retrofit.Builder()
          .baseUrl(URL)
          .addConverterFactory(GsonConverterFactory.create())
          .build()

  fun getApiService() = retrofit.create(
      ApiService::class.java)




}