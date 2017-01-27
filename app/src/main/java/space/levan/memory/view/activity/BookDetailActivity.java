package space.levan.memory.view.activity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;

import butterknife.BindView;
import butterknife.ButterKnife;
import space.levan.memory.R;
import space.levan.memory.bean.douban.BookInfoResponse;
import space.levan.memory.utils.Blur;
import space.levan.memory.utils.Reflect3DImage;

/**
 * Created by WangZhiYao on 2017-01-23.
 */

public class BookDetailActivity extends BaseActivity {

    @BindView(R.id.iv_book_bg)
    ImageView mIvBookBg;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.tv_book_title)
    TextView mTvBookTitle;
    @BindView(R.id.tv_book_info)
    TextView mTvBookInfo;
    @BindView(R.id.tv_author)
    TextView mTvAuthor;
    @BindView(R.id.tv_subtitle)
    TextView mTvSubtitle;
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
    TextView mTvISBN;
    @BindView(R.id.ll_publish_info)
    LinearLayout mLlPublishInfo;
    @BindView(R.id.tv_book_summary)
    TextView mTvBookSummary;
    @BindView(R.id.iv_book_img)
    ImageView mIvBookImg;
    @BindView(R.id.toolbar_layout)
    CollapsingToolbarLayout mCollapsingLayout;
    @BindView(R.id.app_bar)
    AppBarLayout mAppBar;

    private BookInfoResponse mBookInfoResponse;
    private TextView mTvToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        setContentView(R.layout.activity_book_detail);
        ButterKnife.bind(this);
        super.onCreate(savedInstanceState);
        initToolbar();
    }

    @Override
    protected int getOverridePendingTransitionMode()
    {
        return 2;
    }


    private void initToolbar()
    {
        setSupportActionBar(mToolbar);
        getLayoutInflater().inflate(R.layout.toolbar_book_detail, mToolbar);
        mTvToolbar = (TextView) findViewById(R.id.tv_toolbar_title);
        mAppBar.addOnOffsetChangedListener((appBarLayout, verticalOffset) ->
        {
            if (verticalOffset == -441)
            {
                mTvToolbar.setVisibility(View.VISIBLE);
                mTvToolbar.setText(mBookInfoResponse.getTitle());
            }
            else
            {
                mTvToolbar.setVisibility(View.INVISIBLE);
            }
        });

    }

    @Override
    protected void initEvents()
    {
        mBookInfoResponse = (BookInfoResponse)
                getIntent().getSerializableExtra(BookInfoResponse.serialVersionName);
        Bitmap book_img = getIntent().getParcelableExtra("book_img");
        Bitmap bitmap = Reflect3DImage.skewImage(book_img);
        if (book_img != null)
        {
            mIvBookImg.setImageBitmap(Reflect3DImage.skewImage(bitmap));
            mIvBookBg.setImageBitmap(Blur.apply(book_img));
            mIvBookBg.setAlpha(0.9f);
        }
        else
        {
            Glide.with(this)
                    .load(mBookInfoResponse.getImages().getLarge())
                    .asBitmap()
                    .into(new SimpleTarget<Bitmap>()
                    {
                        @Override
                        public void onResourceReady(Bitmap resource,
                                                    GlideAnimation<? super Bitmap> glideAnimation)
                        {
                            mIvBookImg.setImageBitmap(Reflect3DImage.skewImage(bitmap));
                            mIvBookBg.setImageBitmap(Blur.apply(book_img));
                            mIvBookBg.setAlpha(0.9f);
                        }
                    });
        }

        mTvBookInfo.setText(mBookInfoResponse.getInfoString());
        if (mBookInfoResponse.getAuthor().length > 0)
        {
            mTvAuthor.setText("作者：" + mBookInfoResponse.getAuthor()[0]);
        }
    }
}
