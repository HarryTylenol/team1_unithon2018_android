package app.unithon2018.team1.team1unithon2018android.ui.adapter

import android.annotation.SuppressLint
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.RecyclerView.ViewHolder
import android.view.View
import android.view.ViewGroup
import app.unithon2018.team1.team1unithon2018android.R
import app.unithon2018.team1.team1unithon2018android.ext.ClosureCallback
import app.unithon2018.team1.team1unithon2018android.model.Room
import app.unithon2018.team1.team1unithon2018android.ui.adapter.RoomAdapter.RoomViewHolder
import kotlinx.android.synthetic.main.room_item_view.view.*
import kotlin.properties.Delegates

class RoomAdapter(val itemClickListener: ClosureCallback<Room>) :
    RecyclerView.Adapter<RoomViewHolder>() {

  var list: List<Room> by Delegates.observable(listOf()) { _, _, _ ->
    notifyDataSetChanged()
  }

  class RoomViewHolder(view: View) : ViewHolder(view) {
    @SuppressLint("SetTextI18n")
    fun bind(room: Room) {
      itemView.title.text = room.name
      itemView.date.text = "${room.start_at.split(" ")[0]}~${room.end_at.split(" ")[0]}"
    }
  }

  override fun onCreateViewHolder(viewGroup: ViewGroup, type: Int) =
      RoomViewHolder(View.inflate(viewGroup.context, R.layout.room_item_view, null))

  override fun getItemCount() = list.size

  override fun onBindViewHolder(roomViewHolder: RoomViewHolder, position: Int) {
    roomViewHolder.bind(list[position])
    roomViewHolder.itemView.setOnClickListener {
      itemClickListener(list[position])
    }
  }

}