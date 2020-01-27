package co.id.githubusers;

import android.app.Application;

import co.id.githubusers.di.component.AppComponent;
import co.id.githubusers.di.component.DaggerAppComponent;
import co.id.githubusers.di.module.AppModule;
import co.id.githubusers.di.module.NetworkModule;

/**
 * Created by Laksamana Guntur Dzulfikar.
 * Android Developer
 */

public class CoreApp extends Application {
    private AppComponent appComponent = createAppComponent();

    private static CoreApp instance;

    public static CoreApp get() {
        return instance;
    }

    @Override
    public void onCreate(){
        super.onCreate();
        instance = this;
    }

    protected AppComponent createAppComponent() {
        return appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .networkModule(new NetworkModule())
                .build();
    }

    public AppComponent getAppComponent() {
        return  appComponent;
    }
}
