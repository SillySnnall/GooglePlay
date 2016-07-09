package com.stringbox.googleplay.fragment;

import android.graphics.Color;
import android.os.SystemClock;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.stringbox.googleplay.base.LoadDataFragment;
import com.stringbox.googleplay.util.UiUtil;

/**
 * Created by Administrator on 2016/7/8.
 */
public class AppFragment extends LoadDataFragment{
    /**
     * 加载成功的数据，将数据传递给LoadDataFragment-->LoadDataView做处理
     * @return
     */
    @Override
    protected View onLoadSuccessM() {
        TextView view = new TextView(UiUtil.getContext());
        view.setText("应用网络数据");
        view.setGravity(Gravity.CENTER);
        view.setTextColor(Color.RED);
        view.setTextSize(25);
        return view;
    }

    /**
     * 加载数据，将数据传递给LoadDataFragment-->LoadDataView做处理
     * @return
     */
    @Override
    protected int getDataFromServerM() {
        // 延迟测试效果
        new Thread(new Runnable() {
            @Override
            public void run() {
                SystemClock.sleep(12000);
            }
        }).start();
        return 4;
    }
}
