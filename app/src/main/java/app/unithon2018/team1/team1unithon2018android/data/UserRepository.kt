package app.unithon2018.team1.team1unithon2018android.data

import app.unithon2018.team1.team1unithon2018android.model.Post
import app.unithon2018.team1.team1unithon2018android.model.User
import app.unithon2018.team1.team1unithon2018android.network.ApiService

class UserRepository(var apiService: ApiService) {

  companion object {
    fun instance(apiService: ApiService) = UserRepository(apiService)
  }

  fun requestUser(uid: String): User {
    TODO()
  }

}