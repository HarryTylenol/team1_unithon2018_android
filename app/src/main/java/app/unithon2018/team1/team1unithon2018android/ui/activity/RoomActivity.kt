package app.unithon2018.team1.team1unithon2018android.ui.activity

import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import app.unithon2018.team1.team1unithon2018android.R
import app.unithon2018.team1.team1unithon2018android.data.PostRepository
import app.unithon2018.team1.team1unithon2018android.data.RoomRepository
import app.unithon2018.team1.team1unithon2018android.network.ApiManager
import app.unithon2018.team1.team1unithon2018android.ui.adapter.RoomAdapter
import app.unithon2018.team1.team1unithon2018android.ui.adapter.RoomPagerAdapter
import kotlinx.android.synthetic.main.activity_room.*

class RoomActivity : AppCompatActivity() {

  private val apiService = ApiManager.getApiService()

  private val roomRepository by lazy { RoomRepository.instance(apiService) }
  private val postRepository by lazy { PostRepository.instance(apiService) }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_room)

    with(room_recycler) {
      layoutManager = LinearLayoutManager(this@RoomActivity, LinearLayoutManager.HORIZONTAL, false)
      adapter = RoomAdapter()
    }

    val list = ArrayList<Uri>()
    list.add(Uri.parse("R.drawable.lucy.png"))

    room_viewpager.adapter = RoomPagerAdapter(this, list)

    room_tablayout.setupWithViewPager(room_viewpager, true)
  }

}
