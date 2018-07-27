package app.unithon2018.team1.team1unithon2018android.network

import app.unithon2018.team1.team1unithon2018android.model.Post
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {

  // API Service
  @GET
  fun requestPost(): Call<List<Post>>

}