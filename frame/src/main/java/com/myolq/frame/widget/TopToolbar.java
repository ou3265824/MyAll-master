package com.myolq.frame.widget;


import android.app.Activity;
import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.myolq.frame.R;

/**
 * Created by root on 2017-08-24.
 */
public class TopToolbar extends LinearLayout {

    private Context mContext;
    private TextView title;
    private ImageView back;

    public TopToolbar(Context context) {
        super(context);
        mContext = context;
        initView();
    }

    public TopToolbar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        initView();
    }

    public TopToolbar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        initView();
    }

    private void initView() {
        View mView = inflate(mContext, R.layout.widget_toolbar, this);
        Toolbar toolbar = (Toolbar) mView.findViewById(R.id.toolbar);
        toolbar.setTitle("");
        title = (TextView) mView.findViewById(R.id.title);
        back = (ImageView) mView.findViewById(R.id.back);
    }

    public void setTitle(CharSequence s) {
        title.setText(s);
    }

    public void setOnClickLeftBack(final Activity activity) {
        back.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (activity != null) {
                    activity.finish();
                }
            }
        });
    }


}
