package app.unithon2018.team1.team1unithon2018android.ui.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import app.unithon2018.team1.team1unithon2018android.R
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignIn
import android.content.Intent
import android.support.v4.app.FragmentActivity
import android.util.Log
import app.unithon2018.team1.team1unithon2018android.ext.toast
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.android.synthetic.main.activity_login.*
import com.google.android.gms.auth.api.signin.GoogleSignInOptions


class LoginActivity : AppCompatActivity() {

  private val RC_SIGN_IN = 0
  private val auth = FirebaseAuth.getInstance()

  private var gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
      .requestIdToken(getString(R.string.default_web_client_id))
      .requestEmail()
      .build()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_login)

    // Sign In
    sign_in_button.setOnClickListener {
      toast("Request Google Login")
      val signInIntent = GoogleSignIn.getClient(this, gso).getSignInIntent()
      startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    auth.addAuthStateListener {
      if (it.currentUser != null) {
        // TODO Login Success
        toast("Login Success")
      }
    }
  }

  public override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
    super.onActivityResult(requestCode, resultCode, data)

    if (requestCode == RC_SIGN_IN) {
      val task = GoogleSignIn.getSignedInAccountFromIntent(data)
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

}
