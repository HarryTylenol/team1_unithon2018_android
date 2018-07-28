package app.unithon2018.team1.team1unithon2018android;

import android.app.Application;

public class App extends Application {

    private static App instance;

    @Override
    public void onCreate() {
        super.onCreate();

        instance = this;
    }

    public static App getInstance() {
        return instance;
    }
}
