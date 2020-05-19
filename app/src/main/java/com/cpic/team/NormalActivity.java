package com.cpic.team;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import com.cpic.team.baselib.R;
import com.cpic.team.basetools.base.BaseQMUIActivity;
import com.cpic.team.basetools.view.NormalTitleLayout;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Jianxin on 2019/7/29.
 */

public class NormalActivity extends BaseQMUIActivity {
    @BindView(R.id.layout)
    NormalTitleLayout layout;

    @Override
    protected void getIntentData(Bundle savedInstanceState) {

    }

    @Override
    protected void loadXml() {
        setContentView(R.layout.activity_normal);
        ButterKnife.bind(this);
    }

    @Override
    protected void initView() {
        layout.getTopBar().setTitle("测试测试");
        layout.getTopBar().addLeftBackImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        layout.getTopBar().addRightImageButton(R.mipmap.icon_tab_find, R.id.topbar_right_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.show();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        dialog.dismiss();
                        showSuccessToast("成功啦");
                    }
                }, 2000);
            }
        });
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void registerListener() {

    }

}
