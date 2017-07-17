package space.levan.memory.view.activities;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import space.levan.memory.R;
import space.levan.memory.bean.douban.BookInfoResponse;
import space.levan.memory.utils.Blur;
import space.levan.memory.utils.Reflect3DImage;
import space.levan.memory.utils.UIUtils;
import space.levan.memory.view.base.BaseActivity;

/**
 * Created by WangZhiYao on 2017-04-09.
 */

public class BookDetailActivity extends BaseActivity
{
    @BindView(R.id.iv_book_detail_bg)
    ImageView mIvBookBg;
    @BindView(R.id.iv_book_detail_img)
    ImageView mIvBookImg;
    @BindView(R.id.tv_book_detail_add)
    TextView mTvBookAdd;
    @BindView(R.id.tv_book_detail_title)
    TextView mTvBookTitle;
    @BindView(R.id.tv_book_detail_info)
    TextView mTvBookInfo;
    @BindView(R.id.tv_book_detail_author)
    TextView mTvBookAuthor;
    @BindView(R.id.tv_book_detail_subtitle)
    TextView mTvBookSubtitle;
    @BindView(R.id.tv_book_detail_origin_title)
    TextView mTvBookOriginTitle;
    @BindView(R.id.tv_book_detail_publisher)
    TextView mTvBookPublisher;
    @BindView(R.id.tv_book_detail_translator)
    TextView mTvBookTranslator;
    @BindView(R.id.tv_book_detail_publish_date)
    TextView mTvBookPublishDate;
    @BindView(R.id.tv_book_detail_pages)
    TextView mTvBookPages;
    @BindView(R.id.tv_book_detail_isbn)
    TextView mTvBookIsbn;
    @BindView(R.id.tv_book_detail_summary)
    TextView mTvBookSummary;

    private Boolean isCollect;
    private ProgressDialog mProDialog;
    private BookInfoResponse mBookInfoResponse;

    @Override
    protected int getActTransitionMode()
    {
        return 2;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        Window window = getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        setContentView(R.layout.activity_book_detail);
        ButterKnife.bind(this);
        initEvents();
    }

    private void initEvents()
    {
        mBookInfoResponse = (BookInfoResponse)
                getIntent().getSerializableExtra(BookInfoResponse.serialVersionName);
        mBookInfoResponse.setImage(mBookInfoResponse.getImages().getLarge());
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
                            mIvBookBg.setImageBitmap(Blur.apply(null));
                            mIvBookBg.setAlpha(0.9f);
                        }
                    });
        }

        mTvBookTitle.setText(mBookInfoResponse.getTitle());
        mTvBookInfo.setText(mBookInfoResponse.getInfoString());
        mTvBookAuthor.setText(String.format("作者：%s", mBookInfoResponse.getAuthors()));
        if (mBookInfoResponse.getAuthor().length > 0)
        {
            mBookInfoResponse.setAuthors(mBookInfoResponse.getAuthors());
        }
        mTvBookPublisher.setText(String.format("出版社：%s", mBookInfoResponse.getPublisher()));
        if (mBookInfoResponse.getSubtitle().isEmpty())
        {
            mTvBookSubtitle.setVisibility(View.GONE);
        }
        mTvBookSubtitle.setText(String.format("副标题：%s", mBookInfoResponse.getSubtitle()));
        if (mBookInfoResponse.getOrigin_title().isEmpty())
        {
            mTvBookOriginTitle.setVisibility(View.GONE);
        }
        mTvBookOriginTitle.setText(String.format("原作名：%s", mBookInfoResponse.getOrigin_title()));
        if (mBookInfoResponse.getTranslator().length > 0)
        {
            mTvBookTranslator.setText(String.format("译者：%s", mBookInfoResponse.getTranslators()));
        }
        else
        {
            mTvBookTranslator.setVisibility(View.GONE);
        }
        mTvBookPublishDate.setText(String.format("出版日期：%s", mBookInfoResponse.getPubdate()));
        mTvBookPages.setText(String.format("页数：%s", mBookInfoResponse.getPages()));
        mTvBookIsbn.setText(String.format("ISBN：%s", mBookInfoResponse.getIsbn13()));
        if (!mBookInfoResponse.getSummary().isEmpty())
        {
            mTvBookSummary.setText(String.format("　　%s", mBookInfoResponse.getSummary()));
        }
        else
        {
            mTvBookSummary.setText(UIUtils.getContext().getString(R.string.ac_book_detail_no_summary));
        }
    }
}
