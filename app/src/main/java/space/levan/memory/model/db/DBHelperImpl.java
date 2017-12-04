package space.levan.memory.model.db;

import java.util.List;

import javax.inject.Inject;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import space.levan.memory.model.bean.project.Project;

/**
 * File description
 *
 * @author WangZhiYao
 * @date 2017/10/17
 */

public class DBHelperImpl implements DBHelper {

    public static final String DB_NAME = "Memory.realm";

    private Realm mRealm;

    @Inject
    public DBHelperImpl() {
        mRealm = Realm.getInstance(new RealmConfiguration.Builder()
                .deleteRealmIfMigrationNeeded()
                .name(DB_NAME)
                .build());
    }

    @Override
    public void insertProject(Project project) {
        mRealm.beginTransaction();
        mRealm.copyToRealm(project);
        mRealm.commitTransaction();
    }

    @Override
    public List<Project> getAllProject() {
        return mRealm.where(Project.class).findAll();
    }
}
