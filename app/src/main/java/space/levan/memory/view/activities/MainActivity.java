package space.levan.memory.view.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import space.levan.memory.R;
import space.levan.memory.view.base.BaseActivity;

/**
 * Created by WangZhiYao on 2017-04-09.
 */

public class MainActivity extends BaseActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
                Toast.makeText(this, intentResult.getContents(), Toast.LENGTH_SHORT).show();
                /*
                Intent intent = new Intent(MainActivity.this, SearchActivity.class);
                intent.putExtra("q", intentResult.getContents());
                startActivity(intent);*/
            }
        }
        else
        {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}
