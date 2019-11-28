package space.levan.memory.biz.scan;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import space.levan.memory.R;
import space.levan.memory.annotation.TransitionMode;
import space.levan.memory.biz.base.BaseActivity;
import space.levan.memory.utils.DeviceUtils;
import space.levan.scanner.CaptureManager;
import space.levan.scanner.DecoratedBarcodeView;

/**
 * @author WangZhiYao
 * @date 2019/7/2
 */
public class ScanActivity extends BaseActivity implements DecoratedBarcodeView.TorchListener {

    @BindView(R.id.dbv_barcode_scanner)
    DecoratedBarcodeView mDbvBarcodeScanner;
    @BindView(R.id.btn_scan_switch)
    Button mBtnSwitchLight;

    private CaptureManager mCaptureManager;
    private boolean mIsFlashLightOn;

    @Override
    protected int getTransitionMode() {
        return TransitionMode.TRANSITION_MODE_RIGHT;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan);
        ButterKnife.bind(this);

        initView();
        initData(getIntent(), savedInstanceState);
    }

    private void initView() {
        mDbvBarcodeScanner.setTorchListener(this);
        if (!DeviceUtils.hasFlashLight(this)) {
            mBtnSwitchLight.setVisibility(View.GONE);
        }

        mBtnSwitchLight.setBackgroundResource(R.drawable.ic_flash_light_close);
    }

    private void initData(Intent intent, Bundle savedInstanceState) {
        mCaptureManager = new CaptureManager(this, mDbvBarcodeScanner);
        mCaptureManager.initializeFromIntent(intent, savedInstanceState);
        mCaptureManager.decode();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState, @NonNull PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        mCaptureManager.onSaveInstanceState(outState);
    }

    @OnClick(R.id.iv_toolbar_back)
    public void onBackClicked() {
        finish();
    }

    @Override
    public void onTorchOn() {
        mBtnSwitchLight.setBackgroundResource(R.drawable.ic_flash_light_open);
        mIsFlashLightOn = true;
    }

    @Override
    public void onTorchOff() {
        mBtnSwitchLight.setBackgroundResource(R.drawable.ic_flash_light_close);
        mIsFlashLightOn = false;
    }

    @OnClick(R.id.btn_scan_switch)
    public void openFlashLight() {
        if (mIsFlashLightOn) {
            mDbvBarcodeScanner.setTorchOff();
        } else {
            mDbvBarcodeScanner.setTorchOn();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        mCaptureManager.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return mDbvBarcodeScanner.onKeyDown(keyCode, event) || super.onKeyDown(keyCode, event);
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
