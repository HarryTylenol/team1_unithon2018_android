package app.unithon2018.team1.team1unithon2018android.ui.activity

import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import app.unithon2018.team1.team1unithon2018android.R
import app.unithon2018.team1.team1unithon2018android.data.EventRepository
import app.unithon2018.team1.team1unithon2018android.network.ApiManager
import app.unithon2018.team1.team1unithon2018android.ui.adapter.EventPagerAdapter
import app.unithon2018.team1.team1unithon2018android.ui.adapter.RoomAdapter
import kotlinx.android.synthetic.main.activity_event.*

class EventActivity : AppCompatActivity() {
    private val apiService = ApiManager.getApiService()

    private val roomAdapter by lazy {
        RoomAdapter()
    }

    private val eventRepository by lazy { EventRepository.instance(apiService) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_event)

        with(room_recycler) {
            layoutManager = android.support.v7.widget.LinearLayoutManager(this@EventActivity, android.support.v7.widget.LinearLayoutManager.HORIZONTAL, false)
            adapter = roomAdapter
        }

        val list = ArrayList<Uri>()
//    list.add(Uri.parse("R.drawable.lucy.png"))

        room_viewpager.adapter = EventPagerAdapter(this, list)

        room_tablayout.setupWithViewPager(room_viewpager, true)

        fetchEvent()
    }

    fun fetchEvent() {
        eventRepository.fetchEvent(1, { it ->
            val event = it
        })
    }


}
