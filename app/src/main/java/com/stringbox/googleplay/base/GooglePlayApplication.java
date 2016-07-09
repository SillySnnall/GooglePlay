package com.stringbox.googleplay.base;

import android.app.Application;

import com.stringbox.googleplay.util.UiUtil;

/**
 * 初始化一些工具，日志，性能检测，用户统计工具
 */
public class GooglePlayApplication extends Application{
    /**
     * 启动APP第一时间执行这个方法
     */
    @Override
    public void onCreate() {
        super.onCreate();
        UiUtil.init(this);
    }
}
