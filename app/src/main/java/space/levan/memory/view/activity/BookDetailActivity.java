package space.levan.memory.view.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import space.levan.memory.R;
import space.levan.memory.api.presenter.impl.BookDetailPresenterImpl;
import space.levan.memory.api.view.IBookDetailView;

/**
 * 图书详情
 * <p>
 * Created by WangZhiYao on 2016/10/23.
 */

public class BookDetailActivity extends BaseActivity implements IBookDetailView {

    @BindView(R.id.iv_book_bg)
    ImageView mIvBookBg;
    @BindView(R.id.iv_book_img)
    ImageView mIvBookImg;
    @BindView(R.id.fab)
    FloatingActionButton fab;
    @BindView(R.id.toolbar_layout)
    CollapsingToolbarLayout mCollapsingLayout;
    @BindView(R.id.tv_book_author)
    TextView mTvBookAuthor;
    @BindView(R.id.tv_book_translator)
    TextView mTvBookTranslator;
    @BindView(R.id.tv_book_pubdate)
    TextView mTvBookPubDate;
    @BindView(R.id.tv_book_publisher)
    TextView mTvBookPublisher;
    @BindView(R.id.tv_book_isbn)
    TextView mTvBookIsbn;
    @BindView(R.id.tv_book_summary)
    TextView mTvBookSummary;

    private String mBookImg;
    private String mBookTitle;
    private String mBookISBN13;
    private String mBookAuthor;
    private String mBookPubDate;
    private String mBookSummary;
    private String mBookPublisher;
    private String mBookTranslator;

    private ProgressDialog mProDialog;
    private BookDetailPresenterImpl mBookDetailPresenterImpl;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_detail);
        ButterKnife.bind(this);

        fab.setOnClickListener(v -> Snackbar.make(v, "click", Snackbar.LENGTH_LONG).show());
    }

    @Override
    protected void initEvents()
    {
        Intent i = getIntent();
        String mScanResult = i.getStringExtra("ScanResult");

        mBookDetailPresenterImpl = new BookDetailPresenterImpl(this);
        mBookDetailPresenterImpl.getBookDetail(mScanResult);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_book_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void showMessage(String msg)
    {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
        this.finish();
    }

    @Override
    public void showProgress()
    {
        mProDialog = ProgressDialog.show(BookDetailActivity.this, null, getString(R.string.loading));
    }

    @Override
    public void hideProgress()
    {
        if (mProDialog.isShowing())
            mProDialog.dismiss();
    }

    @Override
    public void updateView(HashMap<String, Object> mBook)
    {
        Log.w("WZY", mBook.toString());
        mBookImg = mBook.get("bookImg").toString();
        mBookTitle = mBook.get("bookTitle").toString();
        mBookISBN13 = mBook.get("bookISBN13").toString();
        mBookAuthor = mBook.get("bookAuthor").toString().replace("[","").replace("]","");
        mBookPubDate = mBook.get("bookPubDate").toString();
        mBookSummary = mBook.get("bookSummary").toString().replaceAll("    ","　　");
        mBookPublisher = mBook.get("bookPublisher").toString();
        mBookTranslator = mBook.get("bookTranslator").toString().replace("[","").replace("]","");

        mCollapsingLayout.setTitle("《" + mBookTitle + "》");
        mTvBookAuthor.setText("作者：" + mBookAuthor);
        mTvBookTranslator.setText("译者：" + mBookTranslator);
        mTvBookPubDate.setText("出版日期：" + mBookPubDate);
        mTvBookPublisher.setText("出版社："+mBookPublisher);
        mTvBookIsbn.setText("ISBN：" + mBookISBN13);
        mTvBookSummary.setText("简介：\n　　" + mBookSummary);

        Glide.with(this)
                .load(mBookImg)
                .asBitmap()
                .into(new SimpleTarget<Bitmap>()
                {
                    @Override
                    public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation)
                    {
                        mIvBookImg.setImageBitmap(resource);
                        mIvBookBg.setImageBitmap(resource);
                    }
                });

        Log.w("WZY", "" + mBookAuthor);
    }
}
