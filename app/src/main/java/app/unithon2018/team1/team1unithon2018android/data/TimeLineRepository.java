package app.unithon2018.team1.team1unithon2018android.data;

import android.util.Log;
import app.unithon2018.team1.team1unithon2018android.model.TimeLine;
import app.unithon2018.team1.team1unithon2018android.network.ApiService;
import java.util.List;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TimeLineRepository {

    private ApiService apiService;

    private static TimeLineRepository repository;

    private String accesToken;

    @NotNull
    public static TimeLineRepository instance(@NotNull String accessToken, @Nullable ApiService apiService) {
        if(repository == null) {
            repository = new TimeLineRepository(accessToken, apiService);
        }

        return repository;
    }

    private TimeLineRepository(String accessToken, ApiService apiService) {
        this.accesToken = accessToken;
        this.apiService = apiService;
    }

    public interface FetchTimelineCallback {
        void onTimeLineFetched(List<TimeLine> timelines);
    }

    public void fetchTimeLine(int page, final FetchTimelineCallback callback) {
        Call<List<TimeLine>> call  = apiService.fetchTimeLine(accesToken, page);
        call.enqueue(new Callback<List<TimeLine>>() {
            @Override
            public void onResponse(Call<List<TimeLine>> call, Response<List<TimeLine>> response) {
                if(response.isSuccessful()) {
                    Log.d("Zxcv", "z");
                    callback.onTimeLineFetched(response.body());
                } else {
                    Log.d("123z", response.message());
                }
            }

            @Override
            public void onFailure(Call<List<TimeLine>> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}
