package space.levan.memory.view.activity;

import android.animation.ObjectAnimator;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;

import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import space.levan.memory.R;
import space.levan.memory.bean.http.douban.BookInfoResponse;
import space.levan.memory.utils.common.Blur;
import space.levan.memory.utils.common.UIUtils;

/**
 * 图书详情
 * <p>
 * Created by WangZhiYao on 2016/10/23.
 */

public class BookDetailActivity extends BaseActivity {

    @BindView(R.id.iv_book_bg)
    ImageView mIvBookBg;
    @BindView(R.id.iv_book_img)
    ImageView mIvBookImg;
    @BindView(R.id.fab)
    FloatingActionButton fab;
    @BindView(R.id.toolbar_layout)
    CollapsingToolbarLayout mCollapsingLayout;
    @BindView(R.id.tv_book_info)
    TextView mTvBookInfo;
    @BindView(R.id.iv_more_info)
    ImageView mIvMoreInfo;
    @BindView(R.id.tv_author)
    TextView mTvAuthor;
    @BindView(R.id.tv_subtitle)
    TextView mTvSubTitle;
    @BindView(R.id.tv_origin_title)
    TextView mTvOriginTitle;
    @BindView(R.id.tv_publisher)
    TextView mTvPublisher;
    @BindView(R.id.tv_translator)
    TextView mTvTranslator;
    @BindView(R.id.tv_publish_date)
    TextView mTvPublishDate;
    @BindView(R.id.tv_pages)
    TextView mTvPages;
    @BindView(R.id.tv_isbn)
    TextView mTvIsbn;
    @BindView(R.id.rl_more_info)
    RelativeLayout mRlMoreInfo;
    @BindView(R.id.ll_publish_info)
    LinearLayout mLlPublishInfo;
    @BindView(R.id.progressBar)
    ProgressBar mProgressBar;
    @BindView(R.id.tv_book_summary)
    TextView mTvBookSummary;

    private boolean flag;
    private BookInfoResponse mBookInfoResponse;
    //模拟加载时间
    private static final int PROGRESS_DELAY_MIN_TIME = 500;
    private static final int PROGRESS_DELAY_SIZE_TIME = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        setContentView(R.layout.activity_book_detail);
        ButterKnife.bind(this);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initEvents()
    {
        mBookInfoResponse = (BookInfoResponse) getIntent().getSerializableExtra(BookInfoResponse.serialVersionName);
        mCollapsingLayout.setTitle(mBookInfoResponse.getTitle());
        Bitmap book_img = getIntent().getParcelableExtra("book_img");
        if (book_img != null) {
            mIvBookImg.setImageBitmap(book_img);
            mIvBookBg.setImageBitmap(Blur.apply(book_img));
            mIvBookBg.setAlpha(0.9f);
        } else {
            Glide.with(this)
                    .load(mBookInfoResponse.getImages().getLarge())
                    .asBitmap()
                    .into(new SimpleTarget<Bitmap>()
                    {
                        @Override
                        public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation)
                        {
                            mIvBookImg.setImageBitmap(book_img);
                            mIvBookBg.setImageBitmap(Blur.apply(book_img));
                            mIvBookBg.setAlpha(0.9f);
                        }
                    });
        }
        fab.setOnClickListener(v -> Snackbar.make(v, "click", Snackbar.LENGTH_LONG).show());

        mTvBookInfo.setText(mBookInfoResponse.getInfoString());
        mRlMoreInfo.setOnClickListener(view -> {
            if (flag) {
                ObjectAnimator.ofFloat(mIvMoreInfo, "rotation", 90, 0).start();
                mProgressBar.setVisibility(View.GONE);
                mLlPublishInfo.setVisibility(View.GONE);
                flag = false;
            } else {
                ObjectAnimator.ofFloat(mIvMoreInfo, "rotation", 0, 90).start();
                mProgressBar.setVisibility(View.VISIBLE);
                new Handler()
                {
                    @Override
                    public void handleMessage(Message msg)
                    {
                        super.handleMessage(msg);
                        if (flag)
                        {
                            mLlPublishInfo.setVisibility(View.VISIBLE);
                            mProgressBar.setVisibility(View.GONE);
                        }
                    }
                }.sendEmptyMessageDelayed(0, getDelayTime());
                flag = true;
            }
        });

        if (mBookInfoResponse.getAuthor().length > 0) {
            mTvAuthor.setText("作者：" + mBookInfoResponse.getAuthor()[0]);
        }
        mTvPublisher.setText("出版社" + mBookInfoResponse.getPublisher());
        if (mBookInfoResponse.getSubtitle().isEmpty()) {
            mTvSubTitle.setVisibility(View.GONE);
        }
        mTvSubTitle.setText("副标题：" + mBookInfoResponse.getSubtitle());
        if (mBookInfoResponse.getOrigin_title().isEmpty()) {
            mTvOriginTitle.setVisibility(View.GONE);
        }
        mTvOriginTitle.setText("原作名：" + mBookInfoResponse.getOrigin_title());
        if (mBookInfoResponse.getTranslator().length > 0) {
            mTvTranslator.setText("译者：" + mBookInfoResponse.getTranslator()[0]);
        } else {
            mTvTranslator.setVisibility(View.GONE);
        }
        mTvPublishDate.setText("出版年：" + mBookInfoResponse.getPubdate());
        mTvPages.setText("页数：" + mBookInfoResponse.getPages());
        mTvIsbn.setText("ISBN：" + mBookInfoResponse.getIsbn13());
        if (!mBookInfoResponse.getSummary().isEmpty()) {
            mTvBookSummary.setText(mBookInfoResponse.getSummary());
        } else {
            mTvBookSummary.setText(UIUtils.getContext().getString(R.string.no_brief));
        }
    }

    private int getDelayTime() {
        return new Random().nextInt(PROGRESS_DELAY_SIZE_TIME) + PROGRESS_DELAY_MIN_TIME;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_book_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
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
}
