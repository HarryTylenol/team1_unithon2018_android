package app.unithon2018.team1.team1unithon2018android.ui.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import app.unithon2018.team1.team1unithon2018android.R
import app.unithon2018.team1.team1unithon2018android.data.EventRepository
import app.unithon2018.team1.team1unithon2018android.data.PostRepository
import app.unithon2018.team1.team1unithon2018android.network.ApiManager
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MainActivity : AppCompatActivity(), OnMapReadyCallback {

  private val apiService = ApiManager.getApiService()

  private val roomRepository by lazy { EventRepository.instance(apiService) }
  private val postRepository by lazy { PostRepository.instance(apiService) }

  lateinit var map: GoogleMap

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    val mapFragment = supportFragmentManager
        .findFragmentById(R.id.map) as SupportMapFragment
    mapFragment.getMapAsync(this)
  }

  override fun onMapReady(googleMap: GoogleMap) {
    map = googleMap

    val sydney = LatLng(-34.0, 151.0)
    map.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
    map.moveCamera(CameraUpdateFactory.newLatLng(sydney))
  }
}
