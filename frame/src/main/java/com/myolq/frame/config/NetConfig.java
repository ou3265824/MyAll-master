package com.myolq.frame.config;

import static android.R.attr.id;

/**
 * Created by Administrator on 2017/1/23.
 */

public interface NetConfig {

    public String APPID_KEY="X-Bmob-Application-Id";
    public String APPID_VALUE="034ddf7a49a4fdd1146c23d162994260";

    public String APIKEY_KEY="X-Bmob-REST-API-Key";
    public String APIKEY_VALUE="3d82db4d622b044cb19b4f287a72431b";

    public String TYPE_KEY="Content-Type";
    public String TYPE_VALUE="application/json";

    public String URL="https://api.bmob.cn/1/" ;
    //用户
    public String USERS=URL+"users";
    //邮箱验证注册
    public String REQUEST_EMAIL_VERIFY=URL+"requestEmailVerify";

}
