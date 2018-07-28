package app.unithon2018.team1.team1unithon2018android.data

import android.content.Context
import app.unithon2018.team1.team1unithon2018android.ext.StringPreference
import app.unithon2018.team1.team1unithon2018android.model.Post
import app.unithon2018.team1.team1unithon2018android.network.ApiService

class PostRepository(val context: Context, var apiService: ApiService) {

  private var accessToken by StringPreference(context)

  companion object {
    fun instance(context: Context, apiService: ApiService) = PostRepository(context, apiService)
  }

  fun requestPost(roomId: String): List<Post> {
    TODO()
  }

}