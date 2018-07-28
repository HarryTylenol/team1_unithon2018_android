package app.unithon2018.team1.team1unithon2018android.ui.activity

import android.Manifest
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import app.unithon2018.team1.team1unithon2018android.R
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignIn
import android.content.Intent
import android.support.v4.app.FragmentActivity
import android.util.Log
import android.view.View
import app.unithon2018.team1.team1unithon2018android.data.UserRepository
import app.unithon2018.team1.team1unithon2018android.ext.StringPreference
import app.unithon2018.team1.team1unithon2018android.ext.isVisible
import app.unithon2018.team1.team1unithon2018android.ext.log
import app.unithon2018.team1.team1unithon2018android.ext.mainThread
import app.unithon2018.team1.team1unithon2018android.ext.toast
import app.unithon2018.team1.team1unithon2018android.network.ApiManager
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.android.synthetic.main.activity_login.*
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth.AuthStateListener
import com.google.firebase.auth.FirebaseUser
import com.tbruyelle.rxpermissions2.RxPermissions
import kotlinx.coroutines.experimental.async
import kotlin.math.log

class LoginActivity : AppCompatActivity(), AuthStateListener {

  private var accessToken by StringPreference(this)
  private val permissions by lazy { RxPermissions(this) }
  private val apiService by lazy { ApiManager.getApiService() }
  private val userRepository by lazy { UserRepository.instance(this, apiService = apiService) }

  override fun onAuthStateChanged(auth: FirebaseAuth) {
    if (auth.currentUser != null) {
      // TODO Login Success
      toast("Login Success")
      startActivity(Intent(
          this,
          if (isGranted) MainActivity::class.java
          else PermissionActivity::class.java
      ))
    }
  }

  private fun showLoadUI(isLoading: Boolean) {
    progressbar.isVisible = isLoading
    sign_in_button.isVisible = !isLoading
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_login)

    // Sign In
    sign_in_button.setOnClickListener {
      toast("Request Google Login")
      userRepository.signInGoogle()
    }

    if (userRepository.isCurrentUserExist) {
      showLoadUI(true)
      userRepository.currentUser!!.requestUser()
    }
    else {
      showLoadUI(false)
      userRepository.startAuthObserve()
      userRepository.callback = { it.requestUser() }
    }
  }

  private fun FirebaseUser.requestUser() {
    mainThread {
      try {
        showLoadUI(true)
        val access_token = async {
          val data = providerData.first()
          userRepository.requestUser(data.uid, data.email!!, data.displayName!!,
                                     data.photoUrl.toString())

        }

        accessToken = access_token.await().access_token

        log<LoginActivity>(accessToken)

        showLoadUI(false)
        finish()
        startActivity(Intent(
            this@LoginActivity,
            if (isGranted) MainActivity::class.java
            else PermissionActivity::class.java
        ))
      }
      catch (e: Exception) {
        e.printStackTrace()
        showLoadUI(false)
      }
    }
  }

  override fun onStop() {
    super.onStop()
    userRepository.stopAuthObserve()
  }

  private val isGranted
    get() = permissions.isGranted(Manifest.permission.ACCESS_COARSE_LOCATION) &&
        permissions.isGranted(Manifest.permission.READ_EXTERNAL_STORAGE)


}
