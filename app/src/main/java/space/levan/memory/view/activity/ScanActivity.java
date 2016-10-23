package space.levan.memory.view.activity;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.journeyapps.barcodescanner.CaptureManager;
import com.journeyapps.barcodescanner.DecoratedBarcodeView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import space.levan.memory.R;

/**
 * Created by WangZhiYao on 2016/10/22.
 */

public class ScanActivity extends AppCompatActivity
        implements DecoratedBarcodeView.TorchListener {

   /*@BindView(R.id.btn_switch)
    Button mBtSwitch;
    @BindView(R.id.dbv_custom)
    DecoratedBarcodeView mDbvCustom;*/

    private DecoratedBarcodeView mDbvCustom;
    private Button mBtSwitch;
    private CaptureManager captureManager;
    private boolean isLightOn = false;

    // 点击切换闪光灯
    @OnClick(R.id.btn_switch)
    public void onClick() {
        if (isLightOn) {
            mDbvCustom.setTorchOff();
        } else {
            mDbvCustom.setTorchOn();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan);
        ButterKnife.bind(this);


        mDbvCustom = (DecoratedBarcodeView) findViewById(R.id.dbv_custom);
        mBtSwitch = (Button) findViewById(R.id.btn_switch);
        mDbvCustom.setTorchListener(this);

        // 如果没有闪光灯功能，就去掉相关按钮
        if (!hasFlash()) {
            mBtSwitch.setVisibility(View.GONE);
        }

        //重要代码，初始化捕获
        captureManager = new CaptureManager(this, mDbvCustom);
        captureManager.initializeFromIntent(getIntent(), savedInstanceState);
        captureManager.decode();
    }

    // torch 手电筒
    @Override
    public void onTorchOn() {
        Toast.makeText(this, "torch on", Toast.LENGTH_LONG).show();
        isLightOn = true;
    }

    @Override
    public void onTorchOff() {
        Toast.makeText(this, "torch off", Toast.LENGTH_LONG).show();
        isLightOn = false;
    }

    // 判断是否有闪光灯功能
    private boolean hasFlash() {
        return getApplicationContext().getPackageManager()
                .hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH);
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

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        captureManager.onSaveInstanceState(outState);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return mDbvCustom.onKeyDown(keyCode, event) || super.onKeyDown(keyCode, event);
    }
}
