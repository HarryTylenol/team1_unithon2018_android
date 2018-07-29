package app.unithon2018.team1.team1unithon2018android.ui.adapter

import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import app.unithon2018.team1.team1unithon2018android.App
import app.unithon2018.team1.team1unithon2018android.R
import app.unithon2018.team1.team1unithon2018android.ui.activity.PhotoActivity
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.nested_timeline_item_view.view.*

class TimeLineImgAdapter : RecyclerView.Adapter<TimeLineImgAdapter.TimeLineViewHolder>() {

  private val timelineImgs = mutableListOf<String>()

  override fun onCreateViewHolder(viewGroup: ViewGroup, p1: Int): TimeLineViewHolder =
      TimeLineViewHolder(viewGroup)

  override fun getItemCount(): Int = timelineImgs.size

  override fun onBindViewHolder(viewholder: TimeLineViewHolder, position: Int) {
    with(viewholder.itemView) {
      Glide.with(App.getInstance())
          .load("http://52.79.230.255:5000" + timelineImgs[position])
          .into(timeline_img)
      setOnClickListener {
        this.context.startActivity(Intent(this.context, PhotoActivity::class.java).apply {
          putExtra("image", "http://52.79.230.255:5000" + timelineImgs[position])
        })
      }
    }
  }

  var isAdded = false

  fun addImages(files: List<String>) {
    if (isAdded) return
    isAdded = true
    timelineImgs.clear()
    timelineImgs.addAll(files)

    notifyDataSetChanged()
  }

  class TimeLineViewHolder(viewGroup: ViewGroup) : RecyclerView.ViewHolder(
      LayoutInflater.from(viewGroup.context)
          .inflate(R.layout.nested_timeline_item_view, viewGroup, false)
  )
}
