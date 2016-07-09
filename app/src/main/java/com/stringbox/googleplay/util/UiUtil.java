package com.stringbox.googleplay.util;

import android.content.Context;
import android.os.Handler;

/**
 * 处理View的工具类
 */
public class UiUtil {

    private static Context mContext;
    private static Handler mHandler;

    /**
     * GooglePlayApplication 是 Context 的子类
     */
    public static void init(Context googlePlayApplication) {
        // 获取Context
        mContext = googlePlayApplication;
        // 由于Handler每次只会在APP应用停止运行的时候才会回收，
        // 做成成员静态变量，公用一个节省内存
        mHandler = new Handler();
    }

    /**
     * 返回Handler，整个应用只是用一个Handler
     *
     * @return
     */
    public static Handler getHandler() {
        return mHandler;
    }

    /**
     * 返回Context，让整个应用中都可以通过这个方法获取Context
     *
     * @return
     */
    public static Context getContext() {
        return mContext;
    }

    /**
     * 获取strings.xml中的数据
     */
    public static String[] getStringArray(int resId) {
        return mContext.getResources().getStringArray(resId);
    }

    /**
     * 执行一个任务
     * @param runnable
     */
    public static void post(Runnable runnable) {
        mHandler.post(runnable);
    }
}
