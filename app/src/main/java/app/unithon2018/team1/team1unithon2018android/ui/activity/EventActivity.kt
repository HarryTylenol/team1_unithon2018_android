package app.unithon2018.team1.team1unithon2018android.ui.activity

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import app.unithon2018.team1.team1unithon2018android.R
import app.unithon2018.team1.team1unithon2018android.data.EventRepository
import app.unithon2018.team1.team1unithon2018android.ext.StringPreference
import app.unithon2018.team1.team1unithon2018android.model.Event
import app.unithon2018.team1.team1unithon2018android.network.ApiManager
import app.unithon2018.team1.team1unithon2018android.ui.adapter.EventAdapter
import app.unithon2018.team1.team1unithon2018android.ui.adapter.EventFragmentPagerAdapter
import app.unithon2018.team1.team1unithon2018android.ui.adapter.EventPagerAdapter
import kotlinx.android.synthetic.main.activity_event.*

class EventActivity : AppCompatActivity() {

    private lateinit var event: Event

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

        initializeClickListener()
    }

    private fun initializeClickListener() {
        join.setOnClickListener {
            val intent = Intent(this, TimeLineActivity::class.java).apply {
                putExtra("name", event.name)
            }
            startActivity(intent)
        }
    }

    private fun fetchEvent(id: Int) {
        eventRepository.fetchEvent(id) { it ->
            event = it

            name.text = it.name
            location.text = it.location

            val start = it.start_at.replace("-", ".")
            val startDate = start.substring(0, start.indexOf(" "))

            val end = it.end_at.replace("-", ".")
            val endDate = end.substring(5, 10)

            date.text = startDate + "-" + endDate

            count.text = it.members_count.toString()

            eventAdapter.addTags(it.hashtags)

            event_viewpager.adapter = EventFragmentPagerAdapter(supportFragmentManager, it.images)
            room_tablayout.setupWithViewPager(event_viewpager, true)
        }
    }


}
