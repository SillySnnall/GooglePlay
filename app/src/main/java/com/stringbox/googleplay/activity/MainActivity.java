package com.stringbox.googleplay.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;

import com.stringbox.googleplay.R;
import com.stringbox.googleplay.base.BaseActivity;
import com.stringbox.googleplay.fragment.AppFragment;
import com.stringbox.googleplay.fragment.HomeFragment;
import com.stringbox.googleplay.util.UiUtil;

import org.itheima.tabindicator.library.TabIndicator;

public class MainActivity extends BaseActivity {

    private static final String TAG = "MainActivity";
    private DrawerLayout mDl_main_draw;
    private ActionBar mActionBar;
    private ActionBarDrawerToggle mToggle;
    private ViewPager mVp_main_pager;
    private String[] mTitle;
    private TabIndicator mTi_main_indicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initData();

    }

    /**
     * 初始化数据
     */
    private void initData() {
        // 获取title数据
        mTitle = UiUtil.getStringArray(R.array.pagers);

        // 设置ViewPager的适配器
        mVp_main_pager.setAdapter(mAdapter);

        // 设置 TabIndicator的适配器
        mTi_main_indicator.setViewPager(mVp_main_pager);
    }

    /**
     * FragmentPagerAdapter适配器
     */
    private FragmentPagerAdapter mAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {

        /**
         * 获取Fragment的方法
         * @param position
         * @return
         */
        @Override
        public Fragment getItem(int position) {
            Fragment fragment = null;
            switch (position){
                case 0://首页
                    fragment = new HomeFragment();
                    break;
                case 1://应用
                    fragment = new AppFragment();
                    break;
                default:
                    fragment = new AppFragment();
                    break;
            }
            return fragment;
        }

        /**
         * 显示title的数量
         * @return
         */
        @Override
        public int getCount() {
            if (mTitle != null) {
                return mTitle.length;
            }
            return 0;
        }

        /**
         * 对应页面的标题
         * @param position
         * @return
         */
        @Override
        public CharSequence getPageTitle(int position) {
            if(mTitle !=null){
                return mTitle[position];
            }
            return super.getPageTitle(position);
        }
    };

    /**
     * PagerAdapter适配器
     */

    /**
     * 初始化控件
     */
    private void initView() {
        mDl_main_draw = (DrawerLayout) findViewById(R.id.dl_main_draw);
        mVp_main_pager = (ViewPager) findViewById(R.id.vp_main_pager);
        // 关联TabIndicator
        mTi_main_indicator = (TabIndicator) findViewById(R.id.ti_main_indicator);
        // 添加ActionBar
        mActionBar = getSupportActionBar();
        // 显示菜单按钮
        mActionBar.setDisplayHomeAsUpEnabled(true);
        // 抽屉开关
        mToggle = new ActionBarDrawerToggle(this, mDl_main_draw, R.string.open_drawer, R.string.close_drawer);
        // 同步状态
        mToggle.syncState();
        // 抽屉监听
        mDl_main_draw.addDrawerListener(mToggle);
    }

    /**
     * 菜单按钮
     *
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // 这个home的id也就是菜单按钮的id,别忘记少写android
            case android.R.id.home:
                // 设置左菜单打开或关闭
                mToggle.onOptionsItemSelected(item);
        }
        return super.onOptionsItemSelected(item);
    }
}
