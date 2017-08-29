package com.myolq.user.presenter;

import android.util.Log;

import com.lzy.okgo.model.Response;
import com.myolq.frame.callback.StringCallBack;
import com.myolq.user.User;
import com.myolq.user.bean.UserBean;
import com.myolq.user.contract.RegisterContract;
import com.myolq.user.model.RegisterModel;

/**
 * Created by root on 2017-08-24.
 */

public class RegisterPresenter implements RegisterContract.Presenter {


    private  RegisterModel registerModel;
    private  RegisterContract.RegisterView registerView;

    public RegisterPresenter(RegisterContract.RegisterView registerView) {
        registerModel = new RegisterModel();
        this.registerView = registerView;
        this.registerView.setPresenter(this);
    }

    @Override
    public void start() {

    }

    @Override
    public void getRegister(UserBean user) {
        registerModel.getRegister(user, new StringCallBack() {
            @Override
            public void onSuccess(String s) {
                Log.i("test",s);
                registerView.onToast(s);
            }

            @Override
            public void onError(Response response, Exception e) {

            }
        });
    }
}