package app.unithon2018.team1.team1unithon2018android.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import app.unithon2018.team1.team1unithon2018android.App;
import app.unithon2018.team1.team1unithon2018android.R;
import com.bumptech.glide.Glide;

public class EventImageFragment extends Fragment {

    private String imageUrl;

    public static EventImageFragment newInstance(String image) {
        EventImageFragment fragmentFirst = new EventImageFragment();

        Bundle args = new Bundle();
        args.putString("image", image);

        fragmentFirst.setArguments(args);

        return fragmentFirst;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        imageUrl = getArguments().getString("image");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_event_image, container, false);

        ImageView eventImage = view.findViewById(R.id.event_image);

        Log.d("12312z", "http://52.79.230.255:5000" + imageUrl);

        Glide.with(App.getInstance())
                .load("http://52.79.230.255:5000" + imageUrl)
                .into(eventImage);

        return view;
    }
}
