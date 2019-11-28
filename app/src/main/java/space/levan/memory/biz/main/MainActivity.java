package space.levan.memory.biz.main;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import space.levan.memory.R;
import space.levan.memory.annotation.TransitionMode;
import space.levan.memory.biz.base.BaseActivity;
import space.levan.memory.biz.newnote.NewNoteActivity;
import space.levan.memory.biz.widgets.CenterTitleToolbar;

/**
 * @author WangZhiYao
 * @date 2019/7/2
 */
public class MainActivity extends BaseActivity {

    private static final String TAG = "MainActivity";
    private static final long EXIT_INTERVAL_TIME = 2000L;

    @BindView(R.id.ct_toolbar)
    CenterTitleToolbar mCtToolbar;
    @BindView(R.id.rv_note)
    RecyclerView mRvNote;
    @BindView(R.id.fab)
    FloatingActionButton mFab;
    private long mFirstBackTime;

    private MainViewModel mViewModel;
    private BookNoteAdapter mBookNoteAdapter;

    @Override
    protected int getTransitionMode() {
        return TransitionMode.TRANSITION_MODE_DEFAULT;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setTheme(R.style.AppTheme_NoActionBar);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(mCtToolbar);

        initView();
        initData();
    }

    private void initView() {
        mBookNoteAdapter = new BookNoteAdapter();
        mRvNote.setLayoutManager(new LinearLayoutManager(this));
        mRvNote.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        mRvNote.setAdapter(mBookNoteAdapter);
    }

    private void initData() {
        mViewModel = new ViewModelProvider(this).get(MainViewModel.class);
        mViewModel.queryAllBookNote().observe(this, bookNotes -> {
            if (bookNotes != null && !bookNotes.isEmpty()) {
                mBookNoteAdapter.setBookNoteList(bookNotes);
            }
        });
    }

    @OnClick(R.id.fab)
    public void onFabNewNoteClicked() {
        startActivity(new Intent(this, NewNoteActivity.class));
    }

    @Override
    public void onBackPressed() {
        if (System.currentTimeMillis() - mFirstBackTime > EXIT_INTERVAL_TIME) {
            Snackbar.make(mFab, getText(R.string.activity_main_press_again_to_exit), Snackbar.LENGTH_SHORT)
                    .setAction(getText(R.string.activity_main_exit_now), view -> finish()).show();
            mFirstBackTime = System.currentTimeMillis();
        } else {
            finish();
        }
    }
}
