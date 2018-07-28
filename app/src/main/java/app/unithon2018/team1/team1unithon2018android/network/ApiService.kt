package app.unithon2018.team1.team1unithon2018android.network

import app.unithon2018.team1.team1unithon2018android.model.Event
import app.unithon2018.team1.team1unithon2018android.model.Room
import app.unithon2018.team1.team1unithon2018android.model.TokenData
import retrofit2.Call
import retrofit2.http.*

interface ApiService {

  // API Service
  @FormUrlEncoded
  @POST("/login")
  fun requestLogin(
      @Field("uuid") uid: String,
      @Field("email") email: String,
      @Field("name") name: String,
      @Field("image") image: String
  ): Call<TokenData>

  @GET("/events")
  fun requestEvent(
      @Header("access_token") access_token: String,
      @Query("lat") lat: Double,
      @Query("lng") lng: Double
  ): Call<List<Room>>

  @GET("/events/{event_id}")
  fun fetchEvent(
          @Header("access_token") access_token: String,
          @Path("event_id") eventId: Int
  ): Call<Event>

}