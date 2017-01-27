package space.levan.memory.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.avos.avoscloud.AVUser;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import space.levan.memory.App;
import space.levan.memory.R;

public class MainActivity extends BaseActivity {

    @BindView(R.id.content_main)
    LinearLayout mContentMain;

    private long exitTime = 0;

    @Override
    protected int getOverridePendingTransitionMode()
    {
        return 2;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        setContentView(R.layout.activity_main);
        super.onCreate(savedInstanceState);
        initToolbar();
        ButterKnife.bind(this);
    }

    @Override
    protected void initEvents()
    {

    }

    private void initToolbar()
    {
        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getLayoutInflater().inflate(R.layout.toolbar_main, mToolbar);
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

    @OnClick({R.id.fab, R.id.iv_logout, R.id.iv_collection,R.id.iv_search, R.id.iv_scan})
    public void onClick(View view)
    {
        switch (view.getId())
        {
            case R.id.fab:
                Snackbar.make(view, "FAB", Snackbar.LENGTH_SHORT).show();
                break;
            case R.id.iv_logout:
                AVUser.logOut();
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
                this.finish();
                break;
            case R.id.iv_collection:
                startActivity(new Intent(MainActivity.this, BookDetailActivity.class));
                break;
            case R.id.iv_search:
                startActivity(new Intent(MainActivity.this, SearchActivity.class));
                break;
            case R.id.iv_scan:
                customScan();
                break;
        }
    }
}
