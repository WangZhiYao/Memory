package space.levan.memory.view.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.widget.ImageView;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import butterknife.BindView;
import butterknife.ButterKnife;
import space.levan.memory.App;
import space.levan.memory.R;
import space.levan.memory.view.base.BaseActivity;

/**
 * Created by WangZhiYao on 2017-04-09.
 */

public class MainActivity extends BaseActivity
{
    @BindView(R.id.iv_main_logo)
    ImageView mIvLogo;

    private long exitTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    public void customScan()
    {
        new IntentIntegrator(this)
                .setBeepEnabled(false)
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
                Intent intent = new Intent(MainActivity.this, SearchActivity.class);
                intent.putExtra("q", intentResult.getContents());
                startActivity(intent);
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
            Snackbar.make(mIvLogo, getString(R.string.app_exit), Snackbar.LENGTH_SHORT).show();
            exitTime = System.currentTimeMillis();
        }
        else
        {
            this.finish();
            App.quiteApplication();
        }
    }
}
