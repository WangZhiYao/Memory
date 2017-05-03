package space.levan.memory.view.activities;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import space.levan.memory.R;
import space.levan.memory.bean.douban.BookInfoResponse;
import space.levan.memory.utils.Blur;
import space.levan.memory.utils.RealmUtils;
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
                            mIvBookBg.setImageBitmap(Blur.apply(book_img));
                            mIvBookBg.setAlpha(0.9f);
                        }
                    });
        }

        mTvBookTitle.setText(mBookInfoResponse.getTitle());
        mTvBookInfo.setText(mBookInfoResponse.getInfoString());
        mTvBookAuthor.setText("作者：" + mBookInfoResponse.getAuthors());
        if (mBookInfoResponse.getAuthor().length > 0)
        {
            mBookInfoResponse.setAuthors(mBookInfoResponse.getAuthors());
        }
        mTvBookPublisher.setText("出版社：" + mBookInfoResponse.getPublisher());
        if (mBookInfoResponse.getSubtitle().isEmpty())
        {
            mTvBookSubtitle.setVisibility(View.GONE);
        }
        mTvBookSubtitle.setText("副标题：" + mBookInfoResponse.getSubtitle());
        if (mBookInfoResponse.getOrigin_title().isEmpty())
        {
            mTvBookOriginTitle.setVisibility(View.GONE);
        }
        mTvBookOriginTitle.setText("原作名：" + mBookInfoResponse.getOrigin_title());
        if (mBookInfoResponse.getTranslator().length > 0)
        {
            mTvBookTranslator.setText("译者：" + mBookInfoResponse.getTranslators());
        }
        else
        {
            mTvBookTranslator.setVisibility(View.GONE);
        }
        mTvBookPublishDate.setText("出版日期：" + mBookInfoResponse.getPubdate());
        mTvBookPages.setText("页数：" + mBookInfoResponse.getPages());
        mTvBookIsbn.setText("ISBN：" + mBookInfoResponse.getIsbn13());
        if (!mBookInfoResponse.getSummary().isEmpty())
        {
            mTvBookSummary.setText("　　" + mBookInfoResponse.getSummary());
        }
        else
        {
            mTvBookSummary.setText(UIUtils.getContext().getString(R.string.ac_book_detail_no_summary));
        }

        if (RealmUtils.checkCollection(mBookInfoResponse.getIsbn13()))
        {
            mTvBookAdd.setText(getString(R.string.ac_book_detail_delete));
            Drawable drawable = getResources().getDrawable(R.mipmap.ic_like);
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            mTvBookAdd.setCompoundDrawables(drawable, null, null, null);
            isCollect = true;
        }
        else
        {
            mTvBookAdd.setText(getString(R.string.ac_book_detail_add));
            Drawable drawable = getResources().getDrawable(R.mipmap.ic_dontlike);
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            mTvBookAdd.setCompoundDrawables(drawable, null, null, null);
            isCollect = false;
        }
    }

    @OnClick(R.id.tv_book_detail_add)
    public void onViewClicked()
    {
        if (isCollect)
        {
            RealmUtils.cancelCollection(mBookInfoResponse.getIsbn13());
            mTvBookAdd.setText(getString(R.string.ac_book_detail_add));
            Drawable drawable = getResources().getDrawable(R.mipmap.ic_dontlike);
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            mTvBookAdd.setCompoundDrawables(drawable, null, null, null);
            isCollect = false;
        }
        else
        {

            RealmUtils.addCollection(mBookInfoResponse);
            mTvBookAdd.setText(getString(R.string.ac_book_detail_delete));
            Drawable drawable = getResources().getDrawable(R.mipmap.ic_like);
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            mTvBookAdd.setCompoundDrawables(drawable, null, null, null);
            isCollect = true;
        }
    }
}
