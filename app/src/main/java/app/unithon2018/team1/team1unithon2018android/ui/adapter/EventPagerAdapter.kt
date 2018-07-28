package app.unithon2018.team1.team1unithon2018android.ui.adapter

import android.content.Context
import android.net.Uri
import android.support.v4.view.PagerAdapter
import android.support.v4.view.ViewPager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import app.unithon2018.team1.team1unithon2018android.R
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions


class EventPagerAdapter(context: Context, list: List<String>) : PagerAdapter() {

    private val context = context
    private val list = list

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val view = LayoutInflater.from(context).inflate(R.layout.event_pager_adapter, container, false)

        val roomImage = view.findViewById<ImageView>(R.id.room_image)

        val url = "http://52.79.230.255:5000" + list[position]


        Log.d("z2f", "adsf"  + url)
        Glide.with(context)
                .load(url)
                .apply(RequestOptions().diskCacheStrategy(DiskCacheStrategy.ALL))
                .into(roomImage)

        return view
    }


    override fun isViewFromObject(view: View, any: Any): Boolean = view == any

    override fun getCount(): Int = list.count()

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        (container as ViewPager).removeView(`object` as View)
    }
}
