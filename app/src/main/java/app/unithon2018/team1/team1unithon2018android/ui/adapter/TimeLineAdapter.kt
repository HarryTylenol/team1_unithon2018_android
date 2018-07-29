package app.unithon2018.team1.team1unithon2018android.ui.adapter

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.RecyclerView.ViewHolder
import android.view.LayoutInflater
import android.view.ViewGroup
import app.unithon2018.team1.team1unithon2018android.App
import app.unithon2018.team1.team1unithon2018android.R
import app.unithon2018.team1.team1unithon2018android.model.TimeLine
import app.unithon2018.team1.team1unithon2018android.ui.adapter.TimeLineAdapter.PostViewHolder
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.activity_photo.view.*
import kotlinx.android.synthetic.main.timeline_item_view.view.*

class TimeLineAdapter : RecyclerView.Adapter<PostViewHolder>() {

  private val timelines = mutableListOf<TimeLine>()

  private val timeImgAdapter by lazy {
    TimeLineImgAdapter()
  }

  override fun onCreateViewHolder(viewGroup: ViewGroup, type: Int) = PostViewHolder(viewGroup)

  override fun getItemCount() = timelines.size

  override fun onBindViewHolder(postViewHolder: PostViewHolder, position: Int) {
    with(postViewHolder.itemView) {

      val timeLine = timelines[position]
      content.text = timeLine.text

      val hashtags = timeLine.hashtags

      val stringBuilder = StringBuilder()
      for (item in hashtags) {
        stringBuilder.append("#" + item)
      }

      hash_tags.text = stringBuilder.toString()
      user_nickname.text = timeLine.user[0].nickname
      Glide.with(App.getInstance())
          .load("http://52.79.230.255:5000" + timeLine.user[0].image)
          .into(user_img)

      with(nested_timeline_recycler) {
        layoutManager = LinearLayoutManager(App.getInstance(), LinearLayoutManager.HORIZONTAL,
                                            false)
        adapter = timeImgAdapter

      }

      timeImgAdapter.addImages(timeLine.files)

      var isEnabled = false
      lottie.setAnimation("like_button.json")
      lottie.setOnClickListener {
        if (!isEnabled) {
          lottie.playAnimation()
          isEnabled = true
        }
        else {
          lottie.progress = 0f
          isEnabled = false
        }
      }

    }

  }

  fun addTimeLines(timelines: List<TimeLine>) {
    this.timelines.clear()
    this.timelines.addAll(timelines)

    notifyDataSetChanged()
  }

  class PostViewHolder(view: ViewGroup) : ViewHolder(
      LayoutInflater.from(view.context)
          .inflate(R.layout.timeline_item_view, view, false))
}