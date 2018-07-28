package app.unithon2018.team1.team1unithon2018android.ui.activity

import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import app.unithon2018.team1.team1unithon2018android.R
import app.unithon2018.team1.team1unithon2018android.data.EventRepository
import app.unithon2018.team1.team1unithon2018android.ext.StringPreference
import app.unithon2018.team1.team1unithon2018android.network.ApiManager
import app.unithon2018.team1.team1unithon2018android.ui.adapter.EventAdapter
import app.unithon2018.team1.team1unithon2018android.ui.adapter.EventPagerAdapter
import kotlinx.android.synthetic.main.activity_event.*

class EventActivity : AppCompatActivity() {
    private val apiService = ApiManager.getApiService()

    private val eventAdapter by lazy {
        EventAdapter()
    }

    private val accessToken by StringPreference(this)
    private val eventRepository by lazy { EventRepository.instance(accessToken, apiService) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_event)

        with(event_recycler) {
            layoutManager = android.support.v7.widget.LinearLayoutManager(this@EventActivity, android.support.v7.widget.LinearLayoutManager.HORIZONTAL, false)
            adapter = eventAdapter
        }

        fetchEvent(intent.extras.getInt("id", -1))
    }

    private fun fetchEvent(id: Int) {
        eventRepository.fetchEvent(id) { it ->
            name.text = it.name
            location.text = it.location
            date.text = it.start_at + "-" + it.end_at
            count.text = it.members_count.toString()

            eventAdapter.addTags(it.hashtags)

            Log.d("Zcxv", it.images.count().toString())

            event_viewpager.adapter = EventPagerAdapter(this, it.images)
            room_tablayout.setupWithViewPager(event_viewpager, true)
        }
    }


}
