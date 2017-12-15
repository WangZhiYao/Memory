package space.levan.memory.model.db;

import java.util.List;

import space.levan.memory.model.bean.douban.Books;
import space.levan.memory.model.bean.project.Project;

/**
 * DBHelper interface class
 *
 * @author WangZhiYao
 * @date 2017/10/17
 */

public interface DBHelper {

    /**
     * save project
     *
     * @param project project
     */
    void insertProject(Project project);

    /**
     * fetch all project
     *
     * @return
     */
    List<Project> getAllProject();

    /**
     * save book info
     *
     * @param books book
     */
    void insertBook(Books books);
}
