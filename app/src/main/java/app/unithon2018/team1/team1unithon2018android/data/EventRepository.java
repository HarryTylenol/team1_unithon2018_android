package app.unithon2018.team1.team1unithon2018android.data;

import android.util.Log;
import app.unithon2018.team1.team1unithon2018android.App;
import app.unithon2018.team1.team1unithon2018android.ext.StringPreference;
import app.unithon2018.team1.team1unithon2018android.ext.StringPreferenceCompat;
import app.unithon2018.team1.team1unithon2018android.model.Event;
import app.unithon2018.team1.team1unithon2018android.network.ApiService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EventRepository {

    private ApiService apiService;

    private static EventRepository eventRepository;

    private StringPreferenceCompat pref;

    public static EventRepository instance(ApiService apiService) {
        if(eventRepository == null) {
            eventRepository = new EventRepository(apiService);
        }

        return eventRepository;
    }

    private EventRepository(ApiService apiService) {
        this.apiService = apiService;
        this.pref = new StringPreferenceCompat(App.getInstance());
    }

    public interface FetchEventCallback {

        void onEventFetched(Event event);
    }

    public void fetchEvent(int eventId, final FetchEventCallback eventCallback) {
        Call<Event> call = apiService.fetchEvent(pref.getAccessToken(), eventId);
        call.enqueue(new Callback<Event>() {
            @Override
            public void onResponse(Call<Event> call, Response<Event> response) {
                if(response.isSuccessful()) {
                    eventCallback.onEventFetched(response.body());
                }
            }

            @Override
            public void onFailure(Call<Event> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}
