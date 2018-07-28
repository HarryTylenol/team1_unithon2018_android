package app.unithon2018.team1.team1unithon2018android.data

import android.content.Context
import app.unithon2018.team1.team1unithon2018android.ext.StringPreference
import app.unithon2018.team1.team1unithon2018android.model.Room
import app.unithon2018.team1.team1unithon2018android.network.ApiService
import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.experimental.NonCancellable
import kotlinx.coroutines.experimental.suspendCancellableCoroutine

class RoomRepository(val context: Context, val apiService: ApiService) {

  private var accessToken by StringPreference(context)

  companion object {
    fun instance(context: Context, apiService: ApiService) = RoomRepository(context, apiService)
  }

  suspend fun searchRoom(latlng: LatLng): List<Room> = suspendCancellableCoroutine { continuation ->
    val response = apiService.requestEvent(accessToken, latlng.latitude, latlng.longitude).execute()
    if (!response.isSuccessful) continuation.resumeWithException(
        Exception(response.errorBody()!!.string()))
    else {
      val data = response.body()!!
      continuation.resume(data)
    }

    continuation.invokeOnCancellation {
      if (continuation.isCancelled) {
        try {
          NonCancellable.cancel()
        }
        catch (e: Exception) {

        }
      }
    }
  }

  fun addRoom(room: Room) {
    TODO()
  }

  fun deleteRoom(room: Room) {
    TODO()
  }

}