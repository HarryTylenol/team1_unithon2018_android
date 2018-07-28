package app.unithon2018.team1.team1unithon2018android.ui.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import app.unithon2018.team1.team1unithon2018android.R
import app.unithon2018.team1.team1unithon2018android.network.ApiManager

class MyProfileActivity : AppCompatActivity() {

  private val apiService = ApiManager.getApiService()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_my_profile)
  }
}
