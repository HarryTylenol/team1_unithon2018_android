package app.unithon2018.team1.team1unithon2018android.ui.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import app.unithon2018.team1.team1unithon2018android.R
import app.unithon2018.team1.team1unithon2018android.data.PostRepository
import app.unithon2018.team1.team1unithon2018android.data.RoomRepository
import app.unithon2018.team1.team1unithon2018android.network.ApiManager
import android.widget.Toast
import android.R.menu
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import app.unithon2018.team1.team1unithon2018android.ui.adapter.PostAdapter
import kotlinx.android.synthetic.main.activity_post.*


class PostActivity : AppCompatActivity() {

  private val apiService = ApiManager.getApiService()

  private val postAdapter by lazy {
    PostAdapter()
  }

  private val roomRepository by lazy { RoomRepository.instance(apiService) }
  private val postRepository by lazy { PostRepository.instance(apiService) }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_post)

    with(post_recycler) {
      layoutManager = LinearLayoutManager(this@PostActivity)
      adapter = postAdapter
    }

    fab.setOnClickListener {

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
