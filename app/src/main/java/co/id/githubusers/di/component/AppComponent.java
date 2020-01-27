package co.id.githubusers.di.component;

import javax.inject.Singleton;

import co.id.githubusers.di.module.AppModule;
import co.id.githubusers.di.module.MainActivityModule;
import co.id.githubusers.di.module.NetworkModule;
import dagger.Component;

/**
 * Created by Laksamana Guntur Dzulfikar.
 * Android Developer
 */

@Singleton
@Component(
        modules = {
                AppModule.class,
                NetworkModule.class
        }
)

public interface AppComponent {
    MainComponent plus(MainActivityModule mainActivityModule);
}
