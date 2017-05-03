package space.levan.memory.view.activities;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import space.levan.memory.R;
import space.levan.memory.bean.douban.BookInfoResponse;
import space.levan.memory.bean.douban.ImageBean;
import space.levan.memory.common.Constant;
import space.levan.memory.utils.RealmUtils;
import space.levan.memory.view.base.BaseActivity;

/**
 * Created by WangZhiYao on 2017/5/3.
 */

public class AddCollectionActivity extends BaseActivity
{
    @BindView(R.id.et_add_collection_author)
    EditText mEtAuthor;
    @BindView(R.id.et_add_collection_title)
    EditText mEtTitle;
    @BindView(R.id.et_add_collection_origin_title)
    EditText mEtOriginTitle;
    @BindView(R.id.et_add_collection_publisher)
    EditText mEtPublisher;
    @BindView(R.id.et_add_collection_pubdate)
    EditText mEtPubDate;
    @BindView(R.id.et_add_collection_subtitle)
    EditText mEtSubTitle;
    @BindView(R.id.et_add_collection_translator)
    EditText mEtTranslator;
    @BindView(R.id.et_add_collection_pages)
    EditText mEtPages;
    @BindView(R.id.et_add_collection_isbn)
    EditText mEtIsbn;
    @BindView(R.id.et_add_collection_summary)
    EditText mEtSummary;

    @Override
    protected int getActTransitionMode()
    {
        return 2;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acitivity_add_collection);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_add_collection_save)
    public void onViewClicked()
    {
        BookInfoResponse bookInfoResponse = new BookInfoResponse();
        ImageBean imageBean = new ImageBean();
        imageBean.setLarge(Constant.PLACEHOLD_IT);
        String[] authors = new String[1];
        String[] translator = new String[1];
        authors[0] = mEtAuthor.getText().toString();
        translator[0] = mEtTranslator.getText().toString();
        bookInfoResponse.setAuthor(authors);
        bookInfoResponse.setAuthors(mEtAuthor.getText().toString());
        bookInfoResponse.setTitle(mEtTitle.getText().toString());
        bookInfoResponse.setOrigin_title(mEtOriginTitle.getText().toString());
        bookInfoResponse.setImages(imageBean);
        bookInfoResponse.setPublisher(mEtPublisher.getText().toString());
        bookInfoResponse.setPubdate(mEtPubDate.getText().toString());
        bookInfoResponse.setSubtitle(mEtSubTitle.getText().toString());
        bookInfoResponse.setPages(mEtPages.getText().toString());
        bookInfoResponse.setIsbn13(mEtIsbn.getText().toString());
        bookInfoResponse.setSummary(mEtSummary.getText().toString());
        bookInfoResponse.setTranslator(translator);
        bookInfoResponse.setInfoString(authors, mEtPublisher.getText().toString(), "-");
        if (RealmUtils.addCollection(bookInfoResponse))
        {
            Toast.makeText(this, getString(R.string.ac_add_collection_success), Toast.LENGTH_SHORT).show();
            AddCollectionActivity.this.finish();
        }
        else
        {
            Toast.makeText(this, getString(R.string.ac_add_collection_failed), Toast.LENGTH_SHORT).show();
        }
    }
}
