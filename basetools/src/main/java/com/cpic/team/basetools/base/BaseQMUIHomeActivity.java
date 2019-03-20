package com.cpic.team.basetools.base;

import android.annotation.TargetApi;
import android.app.Dialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.cpic.team.basetools.model.LogoutEvent;
import com.qmuiteam.qmui.arch.QMUIActivity;
import com.qmuiteam.qmui.widget.dialog.QMUITipDialog;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * Created by Jianxin on 2019/2/20.
 */

public abstract class BaseQMUIHomeActivity extends QMUIActivity {
    protected Dialog dialog;
    protected SharedPreferences sp;
    protected SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        getIntentData(savedInstanceState);
        dialog = new QMUITipDialog.Builder(this)
                .setIconType(QMUITipDialog.Builder.ICON_TYPE_LOADING)
                .setTipWord("正在加载")
                .create();
        sp = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        editor = sp.edit();
        loadXml();
        initView();
        initData();
        registerListener();
    }


    @TargetApi(19)
    private void setTranslucentStatus(boolean on) {
        Window win = getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    /**
     * 得到上一个Activity传来的Intent数据
     *
     * @param savedInstanceState
     */
    protected abstract void getIntentData(Bundle savedInstanceState);

    /**
     * 加载布局
     */
    protected abstract void loadXml();

    /**
     * 初始化控件
     */
    protected abstract void initView();

    /**
     * 初始化数据
     */
    protected abstract void initData();

    /**
     * 设置监听
     */
    protected abstract void registerListener();


    /**
     * Toast长显示
     *
     * @param msg
     */
    protected void showLongToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    /**
     * Toast短显示
     *
     * @param msg
     */
    protected void showShortToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    /**
     * Toast成功显示
     *
     * @param msg
     */
    protected void showSuccessToast(String msg) {
        final QMUITipDialog dialog = new QMUITipDialog.Builder(this)
                .setIconType(QMUITipDialog.Builder.ICON_TYPE_SUCCESS)
                .setTipWord(msg)
                .create();
        dialog.show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                dialog.dismiss();
            }
        }, 1500);
    }

    /**
     * Toast失败显示
     *
     * @param msg
     */
    protected void showFailedToast(String msg) {
        final QMUITipDialog dialog = new QMUITipDialog.Builder(this)
                .setIconType(QMUITipDialog.Builder.ICON_TYPE_FAIL)
                .setTipWord(msg)
                .create();
        dialog.show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                dialog.dismiss();
            }
        }, 1500);
    }

    protected void showWarningToast(String msg) {
        final QMUITipDialog dialog = new QMUITipDialog.Builder(this)
                .setIconType(QMUITipDialog.Builder.ICON_TYPE_INFO)
                .setTipWord(msg)
                .create();
        dialog.show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                dialog.dismiss();
            }
        }, 1500);
    }


    protected void showFailedDiaLog(String message) {
        new SweetAlertDialog(this, SweetAlertDialog.ERROR_TYPE)
                .setTitleText(message)
                .setConfirmText("确     定").setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
            @Override
            public void onClick(SweetAlertDialog sweetAlertDialog) {
                sweetAlertDialog.dismissWithAnimation();
            }
        }).show();
    }

    protected void showSucessDiaLog(String message) {
        new SweetAlertDialog(this, SweetAlertDialog.SUCCESS_TYPE)
                .setTitleText(message)
                .setConfirmText("确     定").setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
            @Override
            public void onClick(SweetAlertDialog sweetAlertDialog) {
                sweetAlertDialog.dismissWithAnimation();
            }
        }).show();
    }

    protected void showWarningDiaLog(String message) {
        new SweetAlertDialog(this, SweetAlertDialog.WARNING_TYPE)
                .setTitleText(message)
                .setConfirmText("确     定").setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
            @Override
            public void onClick(SweetAlertDialog sweetAlertDialog) {
                sweetAlertDialog.dismissWithAnimation();
            }
        }).show();
    }
}
