package app.unithon2018.team1.team1unithon2018android.ui.adapter

import android.support.v7.widget.RecyclerView
import android.support.v7.widget.RecyclerView.ViewHolder
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import app.unithon2018.team1.team1unithon2018android.R
import app.unithon2018.team1.team1unithon2018android.model.Post
import app.unithon2018.team1.team1unithon2018android.model.TimeLine
import app.unithon2018.team1.team1unithon2018android.ui.adapter.TimeLineAdapter.PostViewHolder
import kotlinx.android.synthetic.main.timeline_item_view.view.*
import kotlin.properties.Delegates

class TimeLineAdapter : RecyclerView.Adapter<PostViewHolder>() {

  private val timelines = mutableListOf<TimeLine>()

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