package co.id.githubusers.di.module;

import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import co.id.githubusers.CoreApp;
import co.id.githubusers.data.DaoMaster;
import co.id.githubusers.data.DaoSession;
import co.id.githubusers.di.scope.ActivityScope;
import co.id.githubusers.helper.Constant;
import co.id.githubusers.model.DataModel;
import co.id.githubusers.network.NetworkService;
import co.id.githubusers.repository.MainRepository;
import co.id.githubusers.ui.main.MainActivity;
import co.id.githubusers.ui.main.MainPresenter;
import dagger.Module;
import dagger.Provides;

/**
 * Created by Laksamana Guntur Dzulfikar.
 * Android Developer
 */

@Module
public class MainActivityModule {
    private MainActivity mainActivity;

    public MainActivityModule(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    @Provides
    @ActivityScope
    MainActivity provideMainActivity() {
        return mainActivity;
    }

    @Provides
    @ActivityScope
    MainRepository provideMainRepository(NetworkService networkService) {
        return new MainRepository(networkService);
    }

    @Provides
    @ActivityScope
    DaoSession provideDaoSession() {
        String DbName = Constant.DATABASE_NAME;
        DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(CoreApp.get(), DbName);
        Log.d("New DB Name: ", DbName);
        SQLiteDatabase db = devOpenHelper.getWritableDatabase();
        Log.d("DB PATH", db.getPath());
        DaoMaster daoMaster = new DaoMaster(db);
        return daoMaster.newSession();
    }


    @Provides
    @ActivityScope
    DataModel provideDataModel(DaoSession daoSession){
        return new DataModel(daoSession);
    }

    @Provides
    @ActivityScope
    MainPresenter provideMainPresenter(MainRepository mainRepository, DataModel dataModel) {
        return new MainPresenter(mainRepository, dataModel);
    }
}
