package app.unithon2018.team1.team1unithon2018android.ui.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.location.Location
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import app.unithon2018.team1.team1unithon2018android.R
import app.unithon2018.team1.team1unithon2018android.data.PostRepository
import app.unithon2018.team1.team1unithon2018android.data.RoomRepository
import app.unithon2018.team1.team1unithon2018android.ext.mainThread
import app.unithon2018.team1.team1unithon2018android.network.ApiManager
import app.unithon2018.team1.team1unithon2018android.ui.adapter.RoomAdapter
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MapStyleOptions
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.launch
import com.google.android.gms.location.SettingsClient
import com.google.android.gms.location.LocationSettingsRequest
import com.google.android.gms.location.LocationRequest
import android.os.Looper
import android.support.v4.content.ContextCompat
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.maps.model.CircleOptions


class MainActivity : AppCompatActivity(), OnMapReadyCallback {

  private val apiService = ApiManager.getApiService()

  private val UPDATE_INTERVAL = (10 * 1000).toLong()
  private val FASTEST_INTERVAL: Long = 2000

  private val roomRepository by lazy { RoomRepository.instance(this, apiService) }

  lateinit var map: GoogleMap
  private val locationClient: FusedLocationProviderClient by lazy {
    LocationServices.getFusedLocationProviderClient(this)
  }


  @SuppressLint("MissingPermission")
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    //    setSupportActionBar(toolbar)

    val mapFragment = supportFragmentManager
        .findFragmentById(R.id.map) as SupportMapFragment
    mapFragment.getMapAsync(this)

    refresh_location_button.setOnClickListener {
      refreshLocation()
    }
  }

  @SuppressLint("MissingPermission")
  fun refreshLocation() {
    map.clear()
    locationClient.lastLocation.addOnSuccessListener {
      if (it != null) onLocationUpdated(it)
    }
  }

  @SuppressLint("MissingPermission")
  override fun onMapReady(googleMap: GoogleMap) {
    map = googleMap
    map.setPadding(0, 100, 0, 0)
    map.isMyLocationEnabled = true
    map.uiSettings.isCompassEnabled = false
    map.uiSettings.isMyLocationButtonEnabled = false
    refreshLocation()
  }

  private fun onLocationUpdated(location: Location) {
    val latLng = LatLng(
        location.latitude,
        location.longitude
    )
    val cameraUpdate = CameraUpdateFactory.newLatLngZoom(latLng, 15f)

    mainThread {
      val searchedRoom = async {
        roomRepository.searchRoom(latLng)
      }.await()
      val circleFillColor = ContextCompat.getColor(this@MainActivity, R.color.colorCircleFill)
      searchedRoom.sortedByDescending { it.radius.toLong() }.forEach {
        val alpha = when (it.members_count) {
          in 0..40   -> 20
          in 40..300 -> 40
          else       -> 60
        }
        map.addCircle(CircleOptions()
                          .strokeWidth(8f)
                          .fillColor(
                              Color.argb(
                                  alpha,
                                  Color.red(circleFillColor),
                                  Color.green(circleFillColor),
                                  Color.blue(circleFillColor)
                              )
                          )
                          .center(LatLng(it.lat, it.lng
                          ))
                          .strokeColor(
                              ContextCompat.getColor(this@MainActivity, R.color.colorAccent2))
                          .radius(it.radius.toDouble())
        )
      }
      val adapter = RoomAdapter {
        startActivity(Intent(this@MainActivity, RoomActivity::class.java).apply {
          putExtra("id", it.id)
        })
      }
      adapter.list = searchedRoom
      event_recyclerview.adapter = adapter
    }
    map.moveCamera(cameraUpdate)
  }

}
