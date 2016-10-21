package space.levan.memory.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import space.levan.memory.R;
import space.levan.memory.bean.Book2;
import space.levan.memory.dao.BookDao;
import space.levan.memory.implement.GetBookInfoImplement;
import space.levan.memory.model.OnGetBookInfoListener;
import space.levan.memory.util.GetBookInfo;

/**
 * 主界面
 *
 * Created by WangZhiYao on 2016/10/20.
 */
public class MainActivity extends AppCompatActivity implements OnGetBookInfoListener {

    @Bind(R.id.et_bookName)
    EditText mEtBookName;
    @Bind(R.id.et_bookAuthor)
    EditText mEtBookAuthor;
    @Bind(R.id.et_bookPublisher)
    EditText mEtBookPublisher;

    private List<Book2> mBook2List;
    private GetBookInfoImplement bookInfoImplement;
    private BookDao mBookDao;

    @OnClick({R.id.btn_bookURL, R.id.btn_insert})
    public void onClick(View view)
    {
        switch (view.getId())
        {
            case R.id.btn_bookURL:

                customScan();
                break;
            case R.id.btn_insert:
                String bookName = mEtBookName.getText().toString();
                String bookAuthor = mEtBookAuthor.getText().toString();
                String bookPublisher = mEtBookPublisher.getText().toString();

                Book2 mBook2 = new Book2(bookName, bookAuthor, bookPublisher);

                Log.w("WZY", mBook2.getTitle() + mBook2.getAuthor() + mBook2.getPublisher());
                mBookDao.insert(mBook2);
                mBook2List.add(mBook2);
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        bookInfoImplement = new GetBookInfo();
        mBookDao = new BookDao(this);

        mBook2List = mBookDao.queryAll();
        for (Book2 list : mBook2List)
        {
            Log.w("onMainAct", list.getTitle() +","+ list.getAuthor() + "," + list.getPublisher());
        }
    }

    public void customScan()
    {
        new IntentIntegrator(this)
                .setOrientationLocked(false)
                .setCaptureActivity(ScanActivity.class)
                .initiateScan(); // 初始化扫描
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        IntentResult intentResult = IntentIntegrator.parseActivityResult(requestCode,resultCode,data);
        if(intentResult != null) {
            if(intentResult.getContents() == null) {
                Toast.makeText(this,"内容为空", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this,"扫描成功",Toast.LENGTH_LONG).show();
                // ScanResult 为 获取到的字符串
                String ScanResult = intentResult.getContents();
                Toast.makeText(this, ScanResult , Toast.LENGTH_SHORT).show();

                bookInfoImplement.getBookInfo(ScanResult, this);
            }
        } else {
            super.onActivityResult(requestCode,resultCode,data);
        }
    }

    @Override
    public void onGetInfoSuccess(String response)
    {
        String[] strings = response.split(",");

        for (int i = 0; i < strings.length; i++)
        {
            Log.w("WZY", strings[i]);
        }
        mEtBookName.setText(strings[0]);
        mEtBookAuthor.setText(strings[1]);
        mEtBookPublisher.setText(strings[2]);
    }

    @Override
    public void onGetInfoFailure(String response)
    {

    }
}
