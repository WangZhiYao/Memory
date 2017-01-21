package space.levan.memory.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import space.levan.memory.App;
import space.levan.memory.R;
import space.levan.memory.api.presenter.BookListPresenter;
import space.levan.memory.api.view.IBookListView;
import space.levan.memory.bean.douban.BookListResponse;
import space.levan.memory.utils.AnimationUtils;

public class MainActivity extends AppCompatActivity implements IBookListView {

    @BindView(R.id.content_main)
    RelativeLayout mContentMain;
    @BindView(R.id.et_search)
    EditText mEtSearch;

    private long exitTime = 0;
    private BookListPresenter bookListPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        setContentView(R.layout.activity_main);
        super.onCreate(savedInstanceState);
        initToolbar();
        ButterKnife.bind(this);
        initSearch();
    }

    private void initToolbar()
    {
        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getLayoutInflater().inflate(R.layout.main_toolbar, mToolbar);
    }

    private void initSearch()
    {
        mEtSearch.setOnEditorActionListener((textView, i, keyEvent) ->
        {
            if (i == EditorInfo.IME_ACTION_SEARCH)
            {
                if (TextUtils.isEmpty(textView.getText()))
                {
                    Toast.makeText(MainActivity.this, "isEmpty", Toast.LENGTH_SHORT).show();
                    textView.setAnimation(AnimationUtils.shakeAnimation(2));
                }
                else
                {
                    //TODO: Start Search
                }
            }
            return false;
        });
    }

    public void customScan()
    {
        new IntentIntegrator(this)
                .setOrientationLocked(false)
                .setCaptureActivity(ScanActivity.class)
                .initiateScan();
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        IntentResult intentResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (intentResult != null)
        {
            if (intentResult.getContents() == null)
            {
                //ScanResult is Empty
            }
            else
            {
                //TODO: Start Search
                bookListPresenter = new BookListPresenter(this);
                bookListPresenter.loadBooks(intentResult.getContents().toString(),0,20,"title");
            }
        }
        else
        {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override
    public void onBackPressed()
    {
        if ((System.currentTimeMillis() - exitTime) > 2000)
        {
            Snackbar.make(mContentMain, R.string.exit, Snackbar.LENGTH_SHORT).show();
            exitTime = System.currentTimeMillis();
        }
        else
        {
            this.finish();
            App.quiteApplication();
        }
    }

    @OnClick({R.id.fab, R.id.iv_avatar, R.id.iv_collection, R.id.iv_scan})
    public void onClick(View view)
    {
        switch (view.getId())
        {
            case R.id.fab:
                Snackbar.make(view, "FAB", Snackbar.LENGTH_SHORT).show();
                break;
            case R.id.iv_avatar:
                Toast.makeText(MainActivity.this, "avatar", Toast.LENGTH_SHORT).show();
                break;
            case R.id.iv_collection:
                Toast.makeText(this, "collection", Toast.LENGTH_SHORT).show();
                break;
            case R.id.iv_scan:
                customScan();
                break;
        }
    }

    @Override
    public void showMessage(String msg)
    {
        Log.w("WZY", msg);
    }

    @Override
    public void showProgress()
    {
        Log.w("WZY", "showProgress");
    }

    @Override
    public void hideProgress()
    {
        Log.w("WZY", "hideProgress");
    }

    @Override
    public void refreshData(Object result)
    {
        Log.w("WZY", result.toString());
    }

    @Override
    public void addData(Object result)
    {
        Log.w("WZY", result.toString());
    }
}
