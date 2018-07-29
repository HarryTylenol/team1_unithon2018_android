package app.unithon2018.team1.team1unithon2018android.ui.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import app.unithon2018.team1.team1unithon2018android.App
import app.unithon2018.team1.team1unithon2018android.R
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_photo.*
import kotlinx.android.synthetic.main.nested_timeline_item_view.view.*

class PhotoActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_photo)

    Glide.with(App.getInstance())
        .load(intent.getStringExtra("image"))
        .into(selected_image)

  }
}
