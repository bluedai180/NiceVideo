package com.blue.nicevideo.application;

import android.app.Application;

/**
 * Created by Blue on 2017/10/9.
 *
 * @function 入口，初始化，提供上下文环境
 */

public class BlueApplication extends Application{
    private static BlueApplication mApplication = null;

    @Override
    public void onCreate() {
        super.onCreate();
        mApplication = this;
    }

    public static BlueApplication getInstance() {
        return mApplication;
    }
}
