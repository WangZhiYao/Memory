package space.levan.memory.api.view;

import java.util.HashMap;

/**
 * Created by WangZhiYao on 2016/10/22.
 */

public interface IBookDetailView {

    void showMessage(String msg);

    void showProgress();

    void hideProgress();

    void updateView(HashMap<String, Object> mBook);
}
