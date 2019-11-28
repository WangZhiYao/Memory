package space.levan.memory.biz.search;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import pub.devrel.easypermissions.EasyPermissions;
import space.levan.memory.Constants;
import space.levan.memory.R;
import space.levan.memory.annotation.TransitionMode;
import space.levan.memory.api.model.Book;
import space.levan.memory.biz.base.BaseActivity;
import space.levan.memory.biz.scan.ScanActivity;
import space.levan.memory.biz.widgets.SearchToolbar;
import space.levan.memory.biz.widgets.ToastCompat;
import space.levan.scanner.zxing.IntentIntegrator;
import space.levan.scanner.zxing.IntentResult;

/**
 * @author WangZhiYao
 * @date 2019/7/2
 */
public class SearchActivity extends BaseActivity implements SearchToolbar.OnMenuClickListener,
        EasyPermissions.PermissionCallbacks, SearchResultAdapter.OnBookClickListener {

    @BindView(R.id.st_search_toolbar)
    SearchToolbar mStSearchToolbar;
    @BindView(R.id.iv_empty_img)
    ImageView mIvEmptyImg;
    @BindView(R.id.pb_progress)
    ProgressBar mPbProgress;
    @BindView(R.id.rv_search_result)
    RecyclerView mRvSearchResult;

    private SearchViewModel mViewModel;
    private SearchResultAdapter mSearchResultAdapter;

    public static void start(Context context, @TransitionMode int transitionMode) {
        Intent starter = new Intent(context, SearchActivity.class);
        starter.putExtra(Constants.EXTRA_TRANSITION_MODE, transitionMode);
        context.startActivity(starter);
    }

    public static void startForResult(Activity activity, int requestCode, @TransitionMode int transitionMode) {
        Intent starter = new Intent(activity, SearchActivity.class);
        starter.putExtra(Constants.EXTRA_TRANSITION_MODE, transitionMode);
        activity.startActivityForResult(starter, requestCode);
    }

    @Override
    protected int getTransitionMode() {
        return getIntent().getIntExtra(Constants.EXTRA_TRANSITION_MODE, TransitionMode.TRANSITION_MODE_RIGHT);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);
        setSupportActionBar(mStSearchToolbar);

        initView();
        initData();
    }

    private void initView() {
        mStSearchToolbar.setOnMenuClickListener(this);
        mRvSearchResult.setLayoutManager(new LinearLayoutManager(this));
        mRvSearchResult.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
    }

    private void initData() {
        mViewModel = new ViewModelProvider(this).get(SearchViewModel.class);

        mSearchResultAdapter = new SearchResultAdapter();
        mSearchResultAdapter.setOnBookClickListener(this);
        mRvSearchResult.setAdapter(mSearchResultAdapter);

        mViewModel.getLoadInitialStatus().observe(this, apiResponse -> {
            switch (apiResponse.getStatus()) {
                case LOADING:
                    showLoading(this, getString(R.string.activity_search_searching));
                    break;
                case SUCCESS:
                    hideLoading();
                    break;
                case ERROR:
                    hideLoading();
                    break;
                default:
                    break;
            }
        });

        mViewModel.getLoadRangeStatus().observe(this, apiResponse -> {
            switch (apiResponse.getStatus()) {
                case LOADING:
                    break;
                case SUCCESS:
                    break;
                case ERROR:
                    break;
                default:
                    break;
            }
        });
    }

    private void openScanner(Activity activity) {
        new IntentIntegrator(activity)
                .setBeepEnabled(false)
                .setOrientationLocked(false)
                .setCaptureActivity(ScanActivity.class)
                .addExtra(Constants.EXTRA_TRANSITION_MODE, TransitionMode.TRANSITION_MODE_RIGHT)
                .initiateScan();
    }

    @Override
    public void onSearch(@Nullable String keywords) {
        if (TextUtils.isEmpty(keywords)) {
            ToastCompat.makeText(this, getString(R.string.activity_search_please_enter_the_correct_keyword_or_isbn), Toast.LENGTH_SHORT).show();
            return;
        }

        mViewModel.queryBookByKeywords(keywords).observe(this, books -> mSearchResultAdapter.submitList(books));
    }

    @Override
    public void onScanClicked() {
        if (!EasyPermissions.hasPermissions(this, Manifest.permission.CAMERA)) {
            EasyPermissions.requestPermissions(this, "", Constants.REQUEST_CODE_PERMISSIONS, Manifest.permission.CAMERA);
        } else {
            openScanner(this);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @Override
    public void onPermissionsGranted(int requestCode, @NonNull List<String> perms) {
        for (String perm : perms) {
            if (perm.equals(Manifest.permission.CAMERA)) {
                openScanner(this);
                return;
            }
        }
    }

    @Override
    public void onPermissionsDenied(int requestCode, @NonNull List<String> perms) {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        IntentResult intentResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (intentResult != null) {
            if (!TextUtils.isEmpty(intentResult.getContents())) {
                mStSearchToolbar.setSearchKeywords(intentResult.getContents());
                onSearch(intentResult.getContents());
            } else {
                ToastCompat.makeText(this, getString(R.string.activity_search_scan_result_is_empty), Toast.LENGTH_SHORT).show();
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override
    public void onBookClicked(@NonNull Book book) {
        // TODO: 2019/8/4 点击进入书籍详情界面
        Intent data = new Intent();
        data.putExtra(Constants.EXTRA_BOOK, book);
        setResult(RESULT_OK, data);
        finish();
    }
}
