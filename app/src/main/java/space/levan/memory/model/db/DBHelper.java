package space.levan.memory.model.db;

import java.util.List;

import space.levan.memory.model.bean.project.Project;

/**
 * File description
 *
 * @author WangZhiYao
 * @date 2017/10/17
 */

public interface DBHelper {

    void insertProject(Project project);

    List<Project> getAllProject();
}
