package space.levan.memory.di.component;

import javax.inject.Singleton;

import dagger.Component;
import space.levan.memory.app.App;
import space.levan.memory.di.module.AppModule;
import space.levan.memory.di.module.HttpModule;
import space.levan.memory.model.DataManager;
import space.levan.memory.model.db.DBHelperImpl;
import space.levan.memory.model.http.HttpHelperImpl;
import space.levan.memory.model.prefs.SPrefsHelperImpl;

/**
 * File description
 *
 * @author WangZhiYao
 * @date 2017/10/17
 */

@Singleton
@Component(modules = {AppModule.class, HttpModule.class})
public interface AppComponent {

    App getContext();

    DataManager getDataManager();

    DBHelperImpl getRealmHelper();

    HttpHelperImpl getHttpHelper();

    SPrefsHelperImpl getSPrefsHelper();
}
