package app.unithon2018.team1.team1unithon2018android.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import app.unithon2018.team1.team1unithon2018android.ui.EventImageFragment;
import java.util.List;

public class EventFragmentPagerAdapter extends FragmentPagerAdapter {

    private List<String> images;

    public EventFragmentPagerAdapter(FragmentManager fm, List<String> images) {
        super(fm);

        this.images = images;
    }

    @Override
    public Fragment getItem(int position) {
        return EventImageFragment.newInstance(images.get(position));
    }

    @Override
    public int getCount() {
        return images.size();
    }
}
