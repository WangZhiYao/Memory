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

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import space.levan.memory.R;
import space.levan.memory.bean.Book;
import space.levan.memory.dao.BookDao;
import space.levan.memory.util.HtmlParse;

/**
 * 主界面
 *
 * Created by WangZhiYao on 2016/10/20.
 */
public class MainActivity extends AppCompatActivity {

    @Bind(R.id.et_bookName)
    EditText mEtBookName;
    @Bind(R.id.et_bookAuthor)
    EditText mEtBookAuthor;
    @Bind(R.id.et_bookPublisher)
    EditText mEtBookPublisher;

    private List<Book> bookList;
    private BookDao bookDao;

    @OnClick({R.id.btn_bookURL, R.id.btn_insert})
    public void onClick(View view)
    {
        switch (view.getId())
        {
            case R.id.btn_bookURL:
                //String bookURL = mEtBookURL.getText().toString().trim();
                //String bookURL = "http://product.dangdang.com/1900596580.html";
                //try{
                //    Document document = Jsoup.connect(bookURL).userAgent("Mozilla").get();
                //    Log.w("WZY",document.getElementsByTag("meta").get(1).attr("content") + "," +
                //    document.select("img[wsrc$=.jpg]").attr("wsrc") + ",");


                //} catch (IOException e) {
                //    e.printStackTrace();
                //}
                //Document doc = Jsoup.parse("<html><head><meta http-equiv=\"refresh\" " +
                //        "content=\"0;url=http://www.abc.com/event\"/></head></html>");
                //String content = doc.getElementsByTag("meta").get(0).attr("content");
                //Log.w("WZY", content.split("=")[1]);
                customScan();
                break;
            case R.id.btn_insert:
                String bookName = mEtBookName.getText().toString();
                String bookAuthor = mEtBookAuthor.getText().toString();
                String bookPublisher = mEtBookPublisher.getText().toString();

                Book book = new Book(bookName, bookAuthor, bookPublisher);
                bookDao.insert(book);
                //bookList.add(book);
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
                .detectDiskReads().detectDiskWrites().detectNetwork()
                .penaltyLog().build());
        StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
                .detectLeakedSqlLiteObjects().detectLeakedClosableObjects()
                .penaltyLog().penaltyDeath().build());

        bookDao = new BookDao(this);
        bookList = bookDao.queryAll();
        for (Book list : bookList)
        {
            Log.w("onMainAct", list.getBookName() +","+ list.getBookAuthor() + "," + list.getBookPublisher());
        }
    }

    public void customScan()
    {
        new IntentIntegrator(this)
                .setOrientationLocked(false)
                .setCaptureActivity(ScanActivity.class) // 设置自定义的activity是CustomActivity
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
            }
        } else {
            super.onActivityResult(requestCode,resultCode,data);
        }
    }
}
