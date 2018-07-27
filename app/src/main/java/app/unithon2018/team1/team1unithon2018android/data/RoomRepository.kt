package app.unithon2018.team1.team1unithon2018android.data

import app.unithon2018.team1.team1unithon2018android.model.Room
import app.unithon2018.team1.team1unithon2018android.network.ApiManager
import app.unithon2018.team1.team1unithon2018android.network.ApiService

class RoomRepository(val apiService: ApiService) {

  companion object {
    fun instance(apiService: ApiService) = RoomRepository(apiService)
  }

  fun searchRoom(): List<Room> {
    TODO()
  }

  fun addRoom(room: Room) {
    TODO()
  }

  fun deleteRoom(room: Room) {
    TODO()
  }

}