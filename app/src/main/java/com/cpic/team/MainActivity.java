package com.cpic.team;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.view.View;

import com.cpic.team.baselib.R;
import com.cpic.team.basetools.base.BaseQMUIActivity;
import com.qmuiteam.qmui.widget.QMUITabSegment;
import com.qmuiteam.qmui.widget.QMUIViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Jianxin on 2019/7/19.
 */

public class MainActivity extends BaseQMUIActivity {
    @BindView(R.id.pager)
    QMUIViewPager mViewPager;
    @BindView(R.id.tabs)
    QMUITabSegment mTabSegment;
    List<Fragment> fragmentList = new ArrayList<>();

    @Override
    protected void getIntentData(Bundle savedInstanceState) {

    }

    @Override
    protected void loadXml() {
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

    }

    public void background(View view) {

    }

    @Override
    protected void initView() {
        int normalColor = getResources().getColor(R.color.gray_dark);
        int selectColor = getResources().getColor(R.color.colorMain);
        mTabSegment.setDefaultNormalColor(normalColor);
        mTabSegment.setDefaultSelectedColor(selectColor);
        //        mTabSegment.setDefaultTabIconPosition(QMUITabSegment.ICON_POSITION_BOTTOM);

        QMUITabSegment.Tab home = new QMUITabSegment.Tab(ContextCompat.getDrawable(this, R.mipmap.icon_tab_home),
                ContextCompat.getDrawable(this, R.mipmap.icon_tab_home),
                "首页", true);

        // 如果你的 icon 显示大小和实际大小不吻合:
        // 通过 tab.setTabIconBounds 重新设置 bounds
        //        int iconShowSize = QMUIDisplayHelper.dp2px(this, 20);
        //        component.setTabIconBounds(0, 0, iconShowSize, iconShowSize);

        QMUITabSegment.Tab test = new QMUITabSegment.Tab(ContextCompat.getDrawable(this, R.mipmap.icon_tab_find),
                ContextCompat.getDrawable(this, R.mipmap.icon_tab_home),
                "测试", true);
        QMUITabSegment.Tab mine = new QMUITabSegment.Tab(ContextCompat.getDrawable(this, R.mipmap.icon_tab_shop),
                ContextCompat.getDrawable(this, R.mipmap.icon_tab_home),
                "我的", true);


        fragmentList.add(new HomeFragment());
        fragmentList.add(new TestFragment());
        fragmentList.add(new MineFragment());
        mTabSegment.addTab(home)
                .addTab(test)
                .addTab(mine);
        //        mTabSegment.notifyDataChanged();
        mViewPager.setOffscreenPageLimit(5);
        mViewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragmentList.get(position);
            }

            @Override
            public int getCount() {
                return fragmentList.size();
            }
        });
        mTabSegment.setupWithViewPager(mViewPager, false);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void registerListener() {

    }
}
