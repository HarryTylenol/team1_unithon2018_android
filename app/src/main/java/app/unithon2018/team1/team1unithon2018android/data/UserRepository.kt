package app.unithon2018.team1.team1unithon2018android.data

import android.app.Activity
import android.content.Context
import app.unithon2018.team1.team1unithon2018android.R
import app.unithon2018.team1.team1unithon2018android.ext.ClosureCallback
import app.unithon2018.team1.team1unithon2018android.model.Post
import app.unithon2018.team1.team1unithon2018android.model.TokenData
import app.unithon2018.team1.team1unithon2018android.model.User
import app.unithon2018.team1.team1unithon2018android.network.ApiService
import app.unithon2018.team1.team1unithon2018android.ui.activity.LoginActivity
import cn.nekocode.rxactivityresult.RxActivityResult
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuth.AuthStateListener
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.coroutines.experimental.NonCancellable
import kotlinx.coroutines.experimental.suspendCancellableCoroutine

class UserRepository(var activity: LoginActivity, var apiService: ApiService) : AuthStateListener {

  companion object {
    private val RC_SIGN_IN = 0
    fun instance(activity: LoginActivity, apiService: ApiService) =
        UserRepository(activity, apiService)
  }

  private val auth = FirebaseAuth.getInstance()

  private val gso by lazy {
    GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
        .requestIdToken(activity.getString(R.string.default_web_client_id))
        .requestEmail()
        .build()
  }

  lateinit var callback: ClosureCallback<FirebaseUser>

  override fun onAuthStateChanged(auth: FirebaseAuth) {
    if (isCurrentUserExist) callback(auth.currentUser!!)
  }

  fun startAuthObserve() {
    auth.addAuthStateListener(this)
  }

  fun stopAuthObserve() {
    auth.removeAuthStateListener(this)
  }

  fun signInGoogle() {

    RxActivityResult.startActivityForResult(activity,
                                            GoogleSignIn.getClient(activity, gso).getSignInIntent(),
                                            RC_SIGN_IN).subscribe {
      val task = GoogleSignIn.getSignedInAccountFromIntent(it.data)
      try {
        val account = task.getResult(ApiException::class.java)
        val credential = GoogleAuthProvider.getCredential(account.getIdToken(), null);
        auth.signInWithCredential(credential)
      }
      catch (e: ApiException) {
        e.printStackTrace()
      }
    }

  }

  val currentUser get() = auth.currentUser
  val isCurrentUserExist get() = currentUser != null

  suspend fun requestUser(uid: String, email: String, name: String, image: String): TokenData =
      suspendCancellableCoroutine { continuation ->

        val loginResult = apiService.requestLogin(uid, email, name, image).execute()
        val tokenData = loginResult.body()
        if (loginResult.isSuccessful && tokenData != null) {
          continuation.resume(tokenData)
        }
        else {
          continuation.resumeWithException(Exception("${loginResult.code()}::${loginResult.errorBody()?.string()}"))
        }

        continuation.invokeOnCancellation {
          if (continuation.isCancelled) {
            try {
              NonCancellable.cancel()
            }
            catch (e: Exception) {
            }
          }
        }
      }
}