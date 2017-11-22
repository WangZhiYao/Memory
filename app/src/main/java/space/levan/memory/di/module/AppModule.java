package space.levan.memory.di.module;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import space.levan.memory.app.App;
import space.levan.memory.model.DataManager;
import space.levan.memory.model.db.DBHelper;
import space.levan.memory.model.db.DBHelperImpl;
import space.levan.memory.model.http.HttpHelper;
import space.levan.memory.model.http.HttpHelperImpl;
import space.levan.memory.model.prefs.SPrefsHelper;
import space.levan.memory.model.prefs.SPrefsHelperImpl;

/**
 * File description
 *
 * @author WangZhiYao
 * @date 2017/10/17
 */

@Module
public class AppModule {

    private final App mApp;

    public AppModule(App app) {
        this.mApp = app;
    }

    @Provides
    @Singleton
    App provideApp() {
        return mApp;
    }

    @Provides
    @Singleton
    DBHelper provideDBHelperImpl(DBHelperImpl dbHelper) {
        return dbHelper;
    }

    @Provides
    @Singleton
    HttpHelper provideHttpHelperImpl(HttpHelperImpl httpHelper) {
        return httpHelper;
    }

    @Provides
    @Singleton
    SPrefsHelper provideSPrefsHelperImpl(SPrefsHelperImpl sPrefsHelper) {
        return sPrefsHelper;
    }

    @Provides
    @Singleton
    DataManager provideDataManager(DBHelperImpl dbHelper, HttpHelperImpl httpHelper, SPrefsHelperImpl sPrefsHelper) {
        return new DataManager(dbHelper, httpHelper, sPrefsHelper);
    }
}
