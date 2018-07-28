package app.unithon2018.team1.team1unithon2018android.ui.adapter

import android.support.design.chip.ChipDrawable.Delegate
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.RecyclerView.ViewHolder
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import app.unithon2018.team1.team1unithon2018android.R
import app.unithon2018.team1.team1unithon2018android.model.Post
import app.unithon2018.team1.team1unithon2018android.ui.adapter.PostAdapter.PostViewHolder
import kotlin.properties.Delegates

class PostAdapter : RecyclerView.Adapter<PostViewHolder>() {

  val list: List<Post> by Delegates.observable(listOf()) { _, _, _ ->
    notifyDataSetChanged()
  }

  class PostViewHolder(view: View) : ViewHolder(view) {
    fun bind(post: Post) {

    }
  }

  override fun onCreateViewHolder(viewGroup: ViewGroup, type: Int) =
      PostViewHolder(View.inflate(viewGroup.context, R.layout.post_item_view, null))

  override fun getItemCount() = list.size

  override fun onBindViewHolder(postViewHolder: PostViewHolder, position: Int) {
    postViewHolder.bind(list[position])
  }

}