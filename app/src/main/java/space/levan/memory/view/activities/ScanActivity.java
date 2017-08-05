package space.levan.memory.view.activities;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;

import com.journeyapps.barcodescanner.CaptureManager;
import com.journeyapps.barcodescanner.DecoratedBarcodeView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import space.levan.memory.R;
import space.levan.memory.view.base.BaseActivity;

/**
 * Created by WangZhiYao on 2017-04-09.
 */

public class ScanActivity extends BaseActivity implements DecoratedBarcodeView.TorchListener {
    @BindView(R.id.dbv_custom)
    DecoratedBarcodeView mDBV;
    @BindView(R.id.btn_scan_switch)
    Button mSwitchLight;

    private CaptureManager captureManager;
    private boolean isLightOn = false;

    // 点击切换闪光灯
    @OnClick(R.id.btn_scan_switch)
    public void switchLight() {
        if (isLightOn) {
            mDBV.setTorchOff();
        } else {
            mDBV.setTorchOn();
        }
    }

    @Override
    protected int getActTransitionMode() {
        return 2;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan);
        ButterKnife.bind(this);

        mDBV.setTorchListener(this);

        if (!hasFlash()) {
            mSwitchLight.setVisibility(View.GONE);
        }

        mSwitchLight.setBackgroundResource(R.drawable.ic_action_flash_close);

        //重要代码，初始化捕获
        captureManager = new CaptureManager(this, mDBV);
        captureManager.initializeFromIntent(getIntent(), savedInstanceState);
        captureManager.decode();
    }

    private boolean hasFlash() {
        return getApplicationContext().getPackageManager()
                .hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH);
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        captureManager.onSaveInstanceState(outState);
    }

    @Override
    public void onTorchOn() {
        mSwitchLight.setBackgroundResource(R.drawable.ic_action_flash_open);
        isLightOn = true;
    }

    @Override
    public void onTorchOff() {
        mSwitchLight.setBackgroundResource(R.drawable.ic_action_flash_close);
        isLightOn = false;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return mDBV.onKeyDown(keyCode, event) || super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onPause() {
        super.onPause();
        captureManager.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        captureManager.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        captureManager.onDestroy();
    }
}
