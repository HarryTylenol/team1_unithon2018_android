package app.unithon2018.team1.team1unithon2018android.ui.adapter

import android.support.design.chip.ChipDrawable.Delegate
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.RecyclerView.ViewHolder
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import app.unithon2018.team1.team1unithon2018android.R
import app.unithon2018.team1.team1unithon2018android.model.Room
import app.unithon2018.team1.team1unithon2018android.ui.adapter.RoomAdapter.RoomViewHolder
import kotlin.properties.Delegates

class RoomAdapter : RecyclerView.Adapter<RoomViewHolder>() {

  val list: List<Room> by Delegates.observable(listOf()) { _, _, _ ->
    notifyDataSetChanged()
  }

  class RoomViewHolder(view: View) : ViewHolder(view) {
    fun bind(room: Room) {

    }
  }


  override fun onCreateViewHolder(viewGroup: ViewGroup, type: Int) =
      RoomViewHolder(View.inflate(viewGroup.context, R.layout.room_item_view, null))

  override fun getItemCount() = list.size

  override fun onBindViewHolder(roomViewHolder: RoomViewHolder, position: Int) {
    roomViewHolder.bind(list[position])
  }

}