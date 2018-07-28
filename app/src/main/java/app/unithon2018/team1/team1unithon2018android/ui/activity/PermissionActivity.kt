package app.unithon2018.team1.team1unithon2018android.ui.activity

import android.Manifest
import android.Manifest.permission.ACCESS_COARSE_LOCATION
import android.Manifest.permission.READ_EXTERNAL_STORAGE
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import app.unithon2018.team1.team1unithon2018android.R
import app.unithon2018.team1.team1unithon2018android.ext.isVisible
import com.tbruyelle.rxpermissions2.RxPermissions
import kotlinx.android.synthetic.main.activity_permission.*

class PermissionActivity : AppCompatActivity() {

  private val permissions by lazy { RxPermissions(this) }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_permission)

    grant_permissions.setOnClickListener {
      requestPermissions()
    }

  }

  fun requestPermissions() {
    permissions.request(ACCESS_COARSE_LOCATION, READ_EXTERNAL_STORAGE)
        .subscribe {
          if (it) {
            startActivity(Intent(this, MainActivity::class.java))
          }
        }
  }

}
