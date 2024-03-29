package app.unithon2018.team1.team1unithon2018android.data

import android.content.Context
import app.unithon2018.team1.team1unithon2018android.ext.StringPreference
import app.unithon2018.team1.team1unithon2018android.ext.log
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

  suspend fun checkIsOut(eventId: Int): Int? = suspendCancellableCoroutine { continuation ->

    val verification = apiService.leaveEvent(accessToken, eventId).execute()

    if (verification.isSuccessful) {
      continuation.resume(eventId)
    }
    else {
      continuation.resume(null)
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

  suspend fun searchRoom(latlng: LatLng): List<Room> = suspendCancellableCoroutine { continuation ->
    log<RoomRepository>(accessToken)
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

}