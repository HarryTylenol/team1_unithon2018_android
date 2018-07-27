package app.unithon2018.team1.team1unithon2018android.data

import app.unithon2018.team1.team1unithon2018android.model.Post
import app.unithon2018.team1.team1unithon2018android.network.ApiService

class PostRepository(var apiService: ApiService) {

  companion object {
    fun instance(apiService: ApiService) = PostRepository(apiService)
  }

  fun requestPost(roomId: String): List<Post> {
    TODO()
  }

}