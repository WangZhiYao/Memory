package space.levan.memory.view.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import space.levan.memory.R;
import space.levan.memory.api.leancloud.presenter.RegisterPresenter;
import space.levan.memory.api.leancloud.view.IRegisterView;
import space.levan.memory.utils.KeyBoardUtils;

/**
 * Created by WangZhiYao on 2016/12/29.
 */

public class RegisterActivity extends BaseActivity implements IRegisterView {

    @BindView(R.id.et_nice_name)
    EditText mEtNiceName;
    @BindView(R.id.et_username)
    EditText mEtUsername;
    @BindView(R.id.et_password)
    EditText mEtPassword;
    @BindView(R.id.btn_register)
    Button mBtnRegister;

    private ProgressDialog mProDialog;
    private RegisterPresenter registerPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initEvents()
    {

    }

    @OnClick(R.id.btn_register)
    public void onClick()
    {
        if (KeyBoardUtils.isKeyBordOpen(this))
        {
            KeyBoardUtils.closeKeyBord(mEtPassword, this);
        }

        registerPresenter = new RegisterPresenter(this);
        registerPresenter.userRegister(mEtNiceName.getText().toString(),
                mEtUsername.getText().toString(), mEtPassword.getText().toString());
    }

    @Override
    public void showMessage(String msg)
    {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void registerSuccess(String msg)
    {
        Toast.makeText(this, msg + "验证邮箱后才能登录", Toast.LENGTH_LONG).show();
        Intent intent = new Intent();
        intent.putExtra("username", mEtUsername.getText().toString());
        intent.putExtra("password", mEtPassword.getText().toString());
        setResult(1, intent);
        this.finish();
    }

    @Override
    public void registerFailed(String msg)
    {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showProgress()
    {
        mProDialog = ProgressDialog.show(RegisterActivity.this, null, getString(R.string.register_loading));
        mBtnRegister.setClickable(false);
    }

    @Override
    public void hideProgress()
    {
        mProDialog.dismiss();
        mBtnRegister.setClickable(true);
    }
}
