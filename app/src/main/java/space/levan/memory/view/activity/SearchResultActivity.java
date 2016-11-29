package space.levan.memory.view.activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.Toast;

import space.levan.memory.R;
import space.levan.memory.api.view.IBookListView;

/**
 * Created by WangZhiYao on 2016-11-29.
 */

public class SearchResultActivity extends BaseActivity implements IBookListView {

    private ProgressDialog mProDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);
    }

    @Override
    protected void initEvents()
    {

    }

    @Override
    public void showMessage(String msg)
    {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showProgress()
    {
        mProDialog = ProgressDialog.show(SearchResultActivity.this, null, getString(R.string.loading));
    }

    @Override
    public void hideProgress()
    {
        mProDialog.dismiss();
    }

    @Override
    public void updateView(Object response)
    {

    }
}
