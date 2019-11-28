package space.levan.memory.biz.newnote;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.zhihu.matisse.Matisse;

import java.io.File;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTextChanged;
import io.reactivex.CompletableObserver;
import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import pub.devrel.easypermissions.EasyPermissions;
import space.levan.memory.Constants;
import space.levan.memory.R;
import space.levan.memory.annotation.TransitionMode;
import space.levan.memory.api.model.Book;
import space.levan.memory.api.model.Images;
import space.levan.memory.biz.base.BaseActivity;
import space.levan.memory.biz.search.SearchActivity;
import space.levan.memory.biz.widgets.CenterTitleToolbar;
import space.levan.memory.biz.widgets.ToastCompat;
import space.levan.memory.db.model.BookCover;
import space.levan.memory.db.model.BookInfo;
import space.levan.memory.db.model.Note;
import space.levan.memory.log.Logger;
import space.levan.memory.utils.FileUtils;
import space.levan.memory.utils.ImageUtils;
import space.levan.memory.utils.StringUtils;
import space.levan.memory.utils.glide.GlideApp;
import space.levan.memory.utils.ocr.OCRHelper;
import space.levan.memory.utils.ocr.OnOCRInitListener;
import space.levan.memory.utils.ocr.OnOCRResultListener;
import top.zibin.luban.Luban;

/**
 * @author WangZhiYao
 * @date 2019/7/2
 */
public class NewNoteActivity extends BaseActivity implements OnOCRInitListener,
        EasyPermissions.PermissionCallbacks, OnOCRResultListener {

    private static final String TAG = "NewNoteActivity";

    @BindView(R.id.ct_toolbar)
    CenterTitleToolbar mCtToolbar;
    @BindView(R.id.iv_new_note_book_cover)
    ImageView mIvBookCover;
    @BindView(R.id.tv_new_note_add_book_title)
    TextView mTvAddBookTitle;
    @BindView(R.id.tv_new_note_add_book_summary)
    TextView mTvAddBookSummary;
    @BindView(R.id.et_new_note_book_page)
    EditText mEtBookPage;
    @BindView(R.id.et_new_note_book_quotation)
    EditText mEtNewNoteBookQuotation;
    @BindView(R.id.tv_new_note_start_ocr)
    TextView mTvStartOcr;
    @BindView(R.id.et_new_note_content)
    EditText mEtNoteContent;

    private boolean mIsOCRInitSuccess;

    private Book mBook;
    private NewNoteViewModel mViewModel;

    @Override
    protected int getTransitionMode() {
        return TransitionMode.TRANSITION_MODE_RIGHT;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_note);
        ButterKnife.bind(this);
        setSupportActionBar(mCtToolbar);

        initView();
        initData();
    }

    private void initView() {
        mCtToolbar.setOnMenuClickListener(this::saveBookNote);
    }

    private void initData() {
        mViewModel = new ViewModelProvider(this).get(NewNoteViewModel.class);
        OCRHelper.init(this, this);
    }

    @Override
    public void onOCRInitSuccess() {
        mIsOCRInitSuccess = true;
        mTvStartOcr.setVisibility(View.VISIBLE);
    }

    @Override
    public void onOCRInitFailure(@Nullable String msg) {
        mIsOCRInitSuccess = false;
        mTvStartOcr.setVisibility(View.GONE);
    }

    @OnClick(R.id.cl_new_note_book_cover)
    public void onAddBookClicked() {
        SearchActivity.startForResult(this, Constants.REQUEST_CODE_SEARCH_BOOK, TransitionMode.TRANSITION_MODE_LEFT);
    }

    @OnTextChanged(R.id.et_new_note_book_quotation)
    public void onOriginalChanged(CharSequence s) {
        mTvStartOcr.setVisibility(TextUtils.isEmpty(s.toString()) && mIsOCRInitSuccess ? View.VISIBLE : View.GONE);
    }

    @OnClick(R.id.tv_new_note_start_ocr)
    public void onStartOCRClicked() {
        if (!EasyPermissions.hasPermissions(this, Constants.PERMISSIONS)) {
            EasyPermissions.requestPermissions(this, "", Constants.REQUEST_CODE_PERMISSIONS, Constants.PERMISSIONS);
            return;
        }

        ImageUtils.openImageSelector(this, Constants.REQUEST_CODE_IMAGE_SELECTOR);
    }

    @Override
    public void onPermissionsGranted(int requestCode, @NonNull List<String> perms) {
        ImageUtils.openImageSelector(this, Constants.REQUEST_CODE_IMAGE_SELECTOR);
    }

    @Override
    public void onPermissionsDenied(int requestCode, @NonNull List<String> perms) {

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && data != null) {
            if (requestCode == Constants.REQUEST_CODE_SEARCH_BOOK) {
                Book book = data.getParcelableExtra(Constants.EXTRA_BOOK);
                if (book != null) {
                    setBookInfo(book);
                }
            }

            if (requestCode == Constants.REQUEST_CODE_IMAGE_SELECTOR) {
                List<String> imgUrlList = Matisse.obtainPathResult(data);
                if (imgUrlList != null && !imgUrlList.isEmpty()
                        && !TextUtils.isEmpty(imgUrlList.get(0))) {
                    compressImage(imgUrlList.get(0));
                }
            }
        }
    }

    private void compressImage(@NonNull String imgPath) {
        Single.just(imgPath)
                .subscribeOn(Schedulers.io())
                .map(imagePath -> Luban.with(this)
                        .load(imagePath)
                        .setTargetDir(FileUtils.getPrivateFileDir(this) + File.separator + "Pictures")
                        .get()
                        .get(0)
                        .getAbsolutePath())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        showLoading(NewNoteActivity.this, getString(R.string.activity_new_note_identifying));
                    }

                    @Override
                    public void onSuccess(String compressedImagePath) {
                        FileUtils.deleteFile(imgPath);
                        OCRHelper.textRecognizeFromFile(NewNoteActivity.this, compressedImagePath, NewNoteActivity.this);
                    }

                    @Override
                    public void onError(Throwable e) {
                        hideLoading();
                    }
                });
    }

    @Override
    public void onOCRSuccess(String filePath, String result) {
        hideLoading();
        FileUtils.deleteFile(filePath);
        mEtNewNoteBookQuotation.setText(result);
    }

    @Override
    public void onOCRFailure(String msg) {
        hideLoading();
        ToastCompat.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    private void setBookInfo(@NonNull Book book) {
        mBook = book;

        String cover = mBook.getImage();

        Images images = mBook.getImages();
        if (images != null && !TextUtils.isEmpty(images.getSmall())) {
            cover = images.getSmall();
        }

        if (!TextUtils.isEmpty(cover)) {
            GlideApp.with(this)
                    .load(cover)
                    .thumbnail(0.5f)
                    .transition(new DrawableTransitionOptions().crossFade())
                    .centerCrop()
                    .into(mIvBookCover);
        }

        mTvAddBookTitle.setText(StringUtils.nullThenEmpty(mBook.getTitle()));
        mTvAddBookSummary.setText(StringUtils.nullThenEmpty(mBook.getSummary()));
    }

    private void saveBookNote() {
        if (mBook == null || TextUtils.isEmpty(mBook.getIsbn())) {
            ToastCompat.makeText(this, getString(R.string.activity_new_note_invalid_book), Toast.LENGTH_SHORT).show();
            return;
        }

        BookInfo bookInfo = new BookInfo();
        bookInfo.setIsbn(mBook.getIsbn());
        bookInfo.setCover(mBook.getImage());
        bookInfo.setTitle(StringUtils.nullThenEmpty(mBook.getTitle()));
        bookInfo.setAuthors(mBook.getAuthor());
        bookInfo.setAuthorInfo(mBook.getAuthorIntro());
        bookInfo.setSummary(mBook.getSummary());

        BookCover bookCover = new BookCover();
        bookCover.setIsbn(mBook.getIsbn());
        Images images = mBook.getImages();
        if (images != null) {
            bookCover.setLarge(images.getLarge());
            bookCover.setMedium(images.getMedium());
            bookCover.setSmall(images.getSmall());
        }

        Note note = new Note();
        note.setIsbn(mBook.getIsbn());
        String pageStr = mEtBookPage.getText().toString();
        if (!TextUtils.isEmpty(pageStr) && TextUtils.isDigitsOnly(pageStr)) {
            try {
                note.setPageNum(Integer.valueOf(pageStr));
            } catch (Exception ex) {
                Logger.e(TAG, ex);
            }
        }
        note.setQuotation(mEtNewNoteBookQuotation.getText().toString());
        note.setContent(mEtNoteContent.getText().toString());
        note.setCreateTime(System.currentTimeMillis());

        mViewModel.insertBookNote(bookInfo, bookCover, note)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        showLoading(NewNoteActivity.this, getString(R.string.activity_new_note_saving));
                    }

                    @Override
                    public void onComplete() {
                        hideLoading();
                        finish();
                    }

                    @Override
                    public void onError(Throwable e) {
                        hideLoading();
                        Logger.e(TAG, e);
                        ToastCompat.makeText(NewNoteActivity.this, getString(R.string.activity_new_note_save_failed), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        OCRHelper.release(this);
    }
}
