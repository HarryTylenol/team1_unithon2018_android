package app.unithon2018.team1.team1unithon2018android.network

import app.unithon2018.team1.team1unithon2018android.data.EventRepository
import app.unithon2018.team1.team1unithon2018android.model.Event
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {



  @GET("events/{event_id}")
  fun fetchEvent(@Path("event_id") eventId: Int, callback: EventRepository.FetchEventCallback): Call<Event>
}