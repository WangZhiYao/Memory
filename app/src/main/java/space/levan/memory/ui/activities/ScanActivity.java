package space.levan.memory.ui.activities;

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
import space.levan.memory.base.BaseActivity;

/**
 * File description
 *
 * @author WangZhiYao
 * @date 2017/12/17
 */

public class ScanActivity extends BaseActivity implements DecoratedBarcodeView.TorchListener {

    @BindView(R.id.dbv_custom)
    DecoratedBarcodeView mDbvCustom;
    @BindView(R.id.btn_scan_switch)
    Button mBtnSwitchLight;

    private CaptureManager mCaptureManager;
    private boolean isLightOn = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        mDbvCustom.setTorchListener(this);

        if (!hasFlashLight()) {
            mBtnSwitchLight.setVisibility(View.GONE);
        }

        mBtnSwitchLight.setBackgroundResource(R.drawable.ic_flash_light_close);

        mCaptureManager = new CaptureManager(this, mDbvCustom);
        mCaptureManager.initializeFromIntent(getIntent(), savedInstanceState);
        mCaptureManager.decode();
    }

    private boolean hasFlashLight() {
        return getApplicationContext().getPackageManager()
                .hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH);
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        mCaptureManager.onSaveInstanceState(outState);
    }

    @Override
    public void onTorchOn() {
        mBtnSwitchLight.setBackgroundResource(R.drawable.ic_flash_light_open);
        isLightOn = true;
    }

    @Override
    public void onTorchOff() {
        mBtnSwitchLight.setBackgroundResource(R.drawable.ic_flash_light_close);
        isLightOn = false;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return mDbvCustom.onKeyDown(keyCode, event) || super.onKeyDown(keyCode, event);
    }

    @Override
    public void showMessage(String msg) {

    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_scan;
    }

    @Override
    protected int getActTransitionMode() {
        return RIGHT;
    }

    @Override
    protected void initInject() {

    }

    @OnClick(R.id.btn_scan_switch)
    public void openFlashLight() {
        if (isLightOn) {
            mDbvCustom.setTorchOff();
        } else {
            mDbvCustom.setTorchOn();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        mCaptureManager.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mCaptureManager.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mCaptureManager.onDestroy();
    }
}
