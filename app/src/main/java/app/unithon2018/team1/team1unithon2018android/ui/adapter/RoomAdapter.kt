package app.unithon2018.team1.team1unithon2018android.ui.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import app.unithon2018.team1.team1unithon2018android.R
import kotlinx.android.synthetic.main.event_item_view.view.*


internal class RoomAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val tags: MutableList<String> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder = ViewHolder(parent)

    override fun getItemCount(): Int = tags.count()

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