package space.levan.memory.view.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import space.levan.memory.R;

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

    private ProgressDialog mProDialog;

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
}
