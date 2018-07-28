package app.unithon2018.team1.team1unithon2018android.ui.adapter

import android.support.v7.widget.RecyclerView
<<<<<<< HEAD
import android.view.LayoutInflater
import android.view.ViewGroup
import app.unithon2018.team1.team1unithon2018android.R
import kotlinx.android.synthetic.main.event_item_view.view.*
=======
import android.support.v7.widget.RecyclerView.ViewHolder
import android.view.View
import android.view.ViewGroup
import app.unithon2018.team1.team1unithon2018android.R
import app.unithon2018.team1.team1unithon2018android.model.Room
import app.unithon2018.team1.team1unithon2018android.ui.adapter.RoomAdapter.RoomViewHolder
import kotlinx.android.synthetic.main.room_item_view.view.*
import kotlin.properties.Delegates
>>>>>>> 4697b502c4b806dd602c553e063f6623a5cfd2e6


<<<<<<< HEAD
internal class RoomAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val tags: MutableList<String> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder = ViewHolder(parent)

    override fun getItemCount(): Int = tags.count()
=======
  var list: List<Room> by Delegates.observable(listOf()) { _, _, _ ->
    notifyDataSetChanged()
  }

  class RoomViewHolder(view: View) : ViewHolder(view) {
    fun bind(room: Room) {
      itemView.title.text = room.name
    }
  }
>>>>>>> 4697b502c4b806dd602c553e063f6623a5cfd2e6

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        with(holder.itemView) {
            item_tag.text = tags[position]
        }
    }

    fun addTag(tag: String) {
        tags.add(tag)

        notifyDataSetChanged()
    }

    class ViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
            LayoutInflater.from(parent.context)
                    .inflate(R.layout.event_item_view, parent, false))

}