package app.unithon2018.team1.team1unithon2018android.ui.activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import app.unithon2018.team1.team1unithon2018android.R
import app.unithon2018.team1.team1unithon2018android.data.EventRepository
import app.unithon2018.team1.team1unithon2018android.data.TimeLineRepository
import app.unithon2018.team1.team1unithon2018android.ext.StringPreference
import app.unithon2018.team1.team1unithon2018android.network.ApiManager
import app.unithon2018.team1.team1unithon2018android.ui.adapter.TimeLineAdapter
import kotlinx.android.synthetic.main.activity_time_line.*

class TimeLineActivity : AppCompatActivity() {

    private val apiService = ApiManager.getApiService()

    private val timeAdapter by lazy {
        TimeLineAdapter()
    }

    private val accessToken by StringPreference(this)

    private val timeRepository by lazy { TimeLineRepository.instance(accessToken, apiService) }

    private var page = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_time_line)

//        setSupportActionBar(toolbar)

        val timeLineName = intent.getStringExtra("name")
        name.text = timeLineName

        with(timeline_recycler) {
            layoutManager = LinearLayoutManager(this@TimeLineActivity)
            adapter = timeAdapter
            isNestedScrollingEnabled = false
        }

        fetchTimeLines()

        initializeClickListener()
    }

    private fun fetchTimeLines() {
        timeRepository.fetchTimeLine(page++) { it ->
            Log.d("zxcv", page.toString() + "!")
            timeAdapter.addTimeLines(it)
        }
    }

    private fun initializeClickListener() {
        fab.setOnClickListener { view ->
            startActivity(Intent(this, UploadActivity::class.java))
        }

        swipe_refresh.setOnRefreshListener {
            Handler().postDelayed({
                fetchTimeLines()
                swipe_refresh.isRefreshing = false
            }, 3000)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu, menu)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.getItemId()) {
            R.id.search -> {
                return true
            }

            else -> {
                return super.onOptionsItemSelected(item)
            }
        }
    }

}
