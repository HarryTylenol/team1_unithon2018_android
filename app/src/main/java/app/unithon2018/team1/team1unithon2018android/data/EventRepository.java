package app.unithon2018.team1.team1unithon2018android.data;

import android.util.Log;
import app.unithon2018.team1.team1unithon2018android.model.Event;
import app.unithon2018.team1.team1unithon2018android.network.ApiService;

public class EventRepository {

    private ApiService apiService;

    private static EventRepository eventRepository;

    public static EventRepository instance(ApiService apiService) {
        if(eventRepository == null)
            eventRepository = new EventRepository(apiService);

        return eventRepository;
    }

    private EventRepository(ApiService apiService) {
        this.apiService = apiService;
    }

    public interface FetchEventCallback {
        void onEventFetched(Event event);
    }

    public void fetchEvent(int eventId, final FetchEventCallback eventCallback) {
        Log.d("zcx", "11");
        apiService.fetchEvent(eventId, new FetchEventCallback() {
            @Override
            public void onEventFetched(Event event) {
                eventCallback.onEventFetched(event);
            }
        });
    }
}