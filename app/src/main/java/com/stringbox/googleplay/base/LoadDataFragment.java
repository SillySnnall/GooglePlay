package com.stringbox.googleplay.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.stringbox.googleplay.util.UiUtil;
import com.stringbox.googleplay.view.LoadDataView;

/**
 * 具有网络访问功能的Fragment
 */
public abstract class LoadDataFragment extends BaseFragment{

    private LoadDataView mLoadDataView;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // 处理三种网络请求所发生的情况页面 loading/Empty/Error
        // 创建一个类来处理3个页面，避免本类的臃肿
        mLoadDataView = new LoadDataView(UiUtil.getContext()) {
            /**
             * 成功
             * @return
             */
            @Override
            protected View onLoadSuccess() {
                return onLoadSuccessM();
            }

            /**
             * 三种页面，loading/Error/Empty
             * @return
             */
            @Override
            protected int getDataFromServer() {
                // 当前类也无法处理请求，继续把请求抛给子类
                return getDataFromServerM();
            }
        };

        return mLoadDataView;
    }

    /**
     * 子类成功数据加载
     * @return
     */
    protected abstract View onLoadSuccessM();

    /**
     * 子类数据加载
     * @return
     */
    protected abstract int getDataFromServerM();

    /**
     * Activity创建的时候调用
     * @param savedInstanceState
     */
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // 发起网络请求,把这请求交给LoadDataView来处理，方便获取状态
        if (mLoadDataView != null) {
            mLoadDataView.loadData();
        }
    }
}
