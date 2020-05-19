package com.cpic.team;

import com.cpic.team.basetools.base.BaseApplication;
import com.cpic.team.basetools.base.BaseConfig;

/**
 * Created by Jianxin on 2019/7/29.
 */

public class MainApplication extends BaseApplication {
    @Override
    public void onCreate() {
        super.onCreate();
        BaseConfig.init(this);
    }
}
