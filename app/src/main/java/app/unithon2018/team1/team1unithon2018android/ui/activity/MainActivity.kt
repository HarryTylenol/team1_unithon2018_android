package app.unithon2018.team1.team1unithon2018android.ui.activity

import android.annotation.SuppressLint
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
    setSupportActionBar(toolbar)


    val mapFragment = supportFragmentManager
        .findFragmentById(R.id.map) as SupportMapFragment
    mapFragment.getMapAsync(this)

  }

  @SuppressLint("MissingPermission")
  override fun onMapReady(googleMap: GoogleMap) {
    map = googleMap
    map.setPadding(0, 100, 0, 0)
    map.isMyLocationEnabled = true
    map.uiSettings.isCompassEnabled = false
    locationClient.lastLocation.addOnSuccessListener {
      if (it != null) onLocationUpdated(it)
    }
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
      searchedRoom.sortedByDescending { it.radius.toLong() }.forEach {
        map.addCircle(CircleOptions()
                          .strokeWidth(8f)
                          .fillColor(
                              ContextCompat.getColor(this@MainActivity, R.color.colorCircleFill)
                          )
                          .center(LatLng(it.lat, it.lng
                          ))
                          .strokeColor(
                              ContextCompat.getColor(this@MainActivity, R.color.colorAccent))
                          .radius(it.radius.toDouble())
        )
      }
      val adapter = RoomAdapter()
      adapter.list = searchedRoom
      event_recyclerview.adapter = adapter
    }
    map.moveCamera(cameraUpdate)
  }

}
