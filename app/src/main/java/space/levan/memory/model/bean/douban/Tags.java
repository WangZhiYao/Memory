package space.levan.memory.model.bean.douban;

import io.realm.RealmObject;

/**
 * Tags of Book entity
 *
 * @author WangZhiYao
 * @date 2017/12/15
 */

public class Tags extends RealmObject {

    private int count;
    private String name;
    private String title;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "Tags{" +
                "count=" + count +
                ", name='" + name + '\'' +
                ", title='" + title + '\'' +
                '}';
    }
}
