package app.unithon2018.team1.team1unithon2018android.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiManager {

  private val URL = "https://..."

  private val retrofit =
      Retrofit.Builder()
          .baseUrl(URL)
          .addConverterFactory(GsonConverterFactory.create())
          .build()

  fun getApiService() = retrofit.create(
      ApiService::class.java)


}