package com.myolq.frame.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.myolq.frame.utils.ScreenUtils;
import com.myolq.frame.utils.ToastUtil;
import com.myolq.frame.widget.FlexibleLayout;
import com.myolq.frame.widget.LoadDialog;

import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/6/19.
 */

public abstract class BaseActivity extends AppCompatActivity {

    private FlexibleLayout mFlexibleLayout;

    public abstract int getLayoutView();

    public abstract void onCreate();

    public  Context getAContext() {
        return aContext;
    }

    public  Context aContext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getView());
        aContext=this;
        AppManager.getInstance().pushActivity(this);
        ButterKnife.bind(this);
        onCreate();

    }

    private View getView(){
        mFlexibleLayout = new FlexibleLayout(this) {
            @Override
            public int getView() {
                return getLayoutView();
            }

            @Override
            public void onLoadData() {

            }
        };
        mFlexibleLayout.loadData();
        return mFlexibleLayout;
    }

    public void showState(int code) {
        switch (code) {
            case 0:
                mFlexibleLayout.showPageWithState(FlexibleLayout.State.Succeed);
                break;
            case 1:
                mFlexibleLayout.showPageWithState(FlexibleLayout.State.Error);
                break;
            case 2:
                mFlexibleLayout.showPageWithState(FlexibleLayout.State.Load);
                break;
            case 3:
                mFlexibleLayout.showPageWithState(FlexibleLayout.State.Empty);
                break;
            case 4:
                mFlexibleLayout.showPageWithState(FlexibleLayout.State.NoNetWork);
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        AppManager.getInstance().popActivity(this);
    }

    /**
     * 隐藏状态栏
     */
    public void immersion() {
        ScreenUtils.immersion(this);
    }

    public <T extends View> T getViewId(int resId) {
        return (T) super.findViewById(resId);
    }

    public <T extends View> T getViewId(View view, int resId) {
        return (T) view.findViewById(resId);
    }

    public <T extends View> T getViewId(int layout, int resId) {
        return (T) inflate(layout).findViewById(resId);
    }

    public View inflate(int layout) {
        return getLayoutInflater().inflate(layout, null);
    }

    public void LoadShow() {
        LoadDialog.getInstance(this).show();
    }

    public void LoadCancel() {
        LoadDialog.getInstance(this).cancel();
    }
    public void toast(String s) {
        ToastUtil.show(this,s);
    }


}
