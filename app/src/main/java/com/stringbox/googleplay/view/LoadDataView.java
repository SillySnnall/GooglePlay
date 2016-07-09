package com.stringbox.googleplay.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

import com.stringbox.googleplay.R;
import com.stringbox.googleplay.util.UiUtil;

/**
 * 三种页面，loading/Error/Empty
 */
public abstract class LoadDataView extends FrameLayout{
    public static final int STATE_NONE = 0;
    public static final int STATE_LOADING = 1;
    public static final int STATE_ERROR = 2;
    public static final int STATE_EMPTY = 3;
    public static final int STATE_SUCCESS = 4;
    private int mCurrentState = STATE_NONE;
    private View mLoadingView;
    private View mErrorView;
    private View mEmptyView;
    private View mSuccessView;

    /**
     * 类中使用的方法
     * @param context
     */
    public LoadDataView(Context context) {
        this(context, null);
    }

    /**
     * 布局文件是使用的方法
     * @param context
     * @param attrs
     */
    public LoadDataView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
        initData();
    }

    /**
     * 初始化数据
     */
    private void initData() {
        upDateUI();
    }

    /**
     * 更新UI,控制三个状态
     */
    private void upDateUI() {
        // loading的状态
        mLoadingView.setVisibility(mCurrentState == STATE_LOADING ? VISIBLE : GONE);
        // error的状态
        mErrorView.setVisibility(mCurrentState == STATE_ERROR ? VISIBLE : GONE);
        // empty的状态
        mEmptyView.setVisibility(mCurrentState == STATE_EMPTY ? VISIBLE : GONE);
        // succsee的状态
        if(mSuccessView == null && mCurrentState == STATE_SUCCESS) {
            mSuccessView = onLoadSuccess();
        }
        if (mSuccessView != null) {
            // TODO: 2016/7/8 ------ 
//            // 添加SuccessView
//            addView(mSuccessView);
            mSuccessView.setVisibility(mCurrentState == STATE_SUCCESS ? VISIBLE : GONE);
        }
    }

    /**
     * 显示成功的View，子类重写此方法实现
     * @return
     */
    protected abstract View onLoadSuccess();

    /**
     * 初始化控件
     */
    private void initView() {
        // 添加loading
        mLoadingView = View.inflate(UiUtil.getContext(), R.layout.pager_loading, null);
        addView(mLoadingView);
        // 添加Error
        mErrorView = View.inflate(UiUtil.getContext(), R.layout.pager_error, null);
        addView(mErrorView);
        // 添加Empty
        mEmptyView = View.inflate(UiUtil.getContext(), R.layout.pager_empty, null);
        addView(mEmptyView);

    }

    /**
     * 网络数据加载
     */
    public void loadData() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                // 访问服务器，有些应用，数据来源不只是http，
                // 也可以从本地assets，sqlite（内置数据获取，提高效率）中获取
                // 行为都是获取数据，请求的页面方式不是相同的
                // 可以定义一个抽象方法，让子类去实现这个抽象方法，来完成数据加载
                int state = getDataFromServer();
                mCurrentState = state;
                // 在Activity中可以使用runOnUiThread();来更新UI
                // Handler 更新UI
                safeUpdateUI();
            }

            
        }).start();
    }

    /**
     * handler更新UI
     */
    private void safeUpdateUI() {
        // 叫给工具类来更新UI
        UiUtil.post(new Runnable() {
            @Override
            public void run() {
                upDateUI();
            }
        });
    }


    // 请求数据的抽象方法,使用了抽象方法来传递数据
    protected abstract int getDataFromServer();
}
