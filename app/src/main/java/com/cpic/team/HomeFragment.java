package com.cpic.team;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cpic.team.baselib.R;
import com.cpic.team.basetools.utils.ToastUtils;
import com.cpic.team.basetools.view.NormalTitleLayout;
import com.qmuiteam.qmui.util.QMUIStatusBarHelper;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Jianxin on 2019/7/29.
 */

public class HomeFragment extends Fragment {

    @BindView(R.id.layout)
    NormalTitleLayout layout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getActivity(), R.layout.fragment_home, null);
        ButterKnife.bind(this, view);
        layout.getTopBar().setTitle("标题啊");
        layout.getTopBar().addRightTextButton("点击一下试试", R.id.topbar_right_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(),NormalActivity.class));
            }
        });
        return view;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        if (isVisibleToUser) {
            QMUIStatusBarHelper.setStatusBarDarkMode(getActivity());
        }
        super.setUserVisibleHint(isVisibleToUser);
    }

}
