package com.myolq.user;

import android.content.Intent;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;

import com.github.mzule.activityrouter.annotation.Router;
import com.github.mzule.activityrouter.router.Routers;
import com.google.gson.reflect.TypeToken;
import com.myolq.frame.BaseActivity;
import com.myolq.frame.config.RouterConfig;
import com.myolq.frame.utils.CharacterUtils;
import com.myolq.frame.utils.GsonUtils;
import com.myolq.frame.utils.LogUtils;
import com.myolq.frame.utils.ToastUtil;
import com.myolq.frame.widget.LoadDialog;
import com.myolq.frame.widget.TitleBar;
import com.myolq.user.bean.BaseBean;
import com.myolq.user.bean.Us;
import com.myolq.user.bean.UserBean;
import com.myolq.user.contract.LoginContract;
import com.myolq.user.presenter.LoginPresenter;

import java.lang.reflect.Type;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

//import com.myolq.frame.widget.Toolbar;

/**
 * 登录
 */
@Router(RouterConfig.LOGIN)
public class LoginActivity extends BaseActivity implements LoginContract.LoginView {


    @BindView(R2.id.tb_title)
    TitleBar tbTitle;
    @BindView(R2.id.actv_account)
    AutoCompleteTextView actvAccount;
    @BindView(R2.id.et_password)
    EditText etPassword;
    private LoginContract.Presenter presenter;

    @Override
    public int getLayoutView() {
        return R.layout.activity_login;
    }

    @Override
    public void onCreate() {
        ButterKnife.bind(this);
        init();
    }


    private void init() {
        LoginPresenter loginPresenter = new LoginPresenter(this);
//        tbTitle.setTitle("登录");
        tbTitle.setOnClickLeftBack(this);
    }


    @Override
    public void setPresenter(LoginContract.Presenter presenter) {
        this.presenter = presenter;
    }


    @Override
    public void onToast(String s) {
        ToastUtil.show(this, s);
    }

    @Override
    public void onLoadShow() {
        LoadShow();
    }

    @Override
    public void onLoadCancel() {
        LoadCancel();
    }

    @OnClick({R2.id.btn_login,R2.id.tv_register,R2.id.tv_forget_password})
    public void onViewClicked(View view) {
        int id = view.getId();
        if (id == R.id.btn_login) {
            String account = actvAccount.getText().toString().trim();
            String password = etPassword.getText().toString().trim();
            if (CharacterUtils.isEmpty(account)) {
                onToast("账号不能为空");
                return;
            }
            if (CharacterUtils.isEmpty(password)) {
                onToast("密码不能为空");
                return;
            }
            presenter.getLogin(account, password);
//        UserBean userBean=new UserBean(account,password);
        } else if (id == R.id.tv_register) {
//
            Routers.open(this, RouterConfig.getRegister());

        }else if (id==R.id.tv_forget_password){
            Routers.open(this,RouterConfig.getForgetPassword());
        }
    }

    @Override
    public void onLoginSuccess(String s) {
//        onToast(s);
        onLoadCancel();
        BaseBean<List<UserBean>> bean= GsonUtils.getBeanFromJson(s,new TypeToken<BaseBean<List<UserBean>>>() {}.getType());
        if (bean!=null&&bean.getResults()!=null&&bean.getResults().size()>0){
        UserBean userBean=bean.getResults().get(0);
            Intent intent=new Intent();
            intent.putExtra("boy",userBean.getBoy());
            setResult(RESULT_OK,intent);
            finish();
        }
    }
}

