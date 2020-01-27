package co.id.githubusers.di.component;

import co.id.githubusers.di.module.MainActivityModule;
import co.id.githubusers.di.scope.ActivityScope;
import co.id.githubusers.ui.main.MainActivity;
import dagger.Subcomponent;

/**
 * Created by Laksamana Guntur Dzulfikar.
 * Android Developer
 */

@ActivityScope
@Subcomponent(
        modules = MainActivityModule.class
)
public interface MainComponent {
    MainActivity inject(MainActivity mainActivity);
}
