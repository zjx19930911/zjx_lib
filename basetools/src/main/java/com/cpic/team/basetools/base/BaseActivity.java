package com.cpic.team.basetools.base;

import android.annotation.TargetApi;
import android.app.Dialog;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.cpic.team.basetools.model.LogoutEvent;
import com.cpic.team.basetools.utils.ProgressDialogHandle;
import com.readystatesoftware.systembartint.SystemBarTintManager;
import com.sdsmdg.tastytoast.TastyToast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * Created by Taylor on 2016/11/8.
 */
public abstract class BaseActivity extends AppCompatActivity {


    protected Dialog dialog;
    protected SharedPreferences sp;
    protected SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            setTranslucentStatus(true);
            SystemBarTintManager tintManager = new SystemBarTintManager(this);
            tintManager.setStatusBarTintEnabled(false);
            tintManager.setStatusBarTintColor(Color.parseColor(BaseConfig.MainColor));//通知栏所需颜色
        }
        getIntentData(savedInstanceState);
        EventBus.getDefault().register(this);
        dialog = ProgressDialogHandle.getProgressDialog(this, null);
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

    @Subscribe
    public void onEventMainThread(LogoutEvent logoutEvent) {
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
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
        TastyToast.makeText(this, msg, Toast.LENGTH_SHORT, TastyToast.SUCCESS);
    }

    /**
     * Toast失败显示
     *
     * @param msg
     */
    protected void showFailedToast(String msg) {
        TastyToast.makeText(this, msg, Toast.LENGTH_LONG, TastyToast.ERROR);
    }

    protected void showWarningToast(String msg) {
        TastyToast.makeText(this, msg, Toast.LENGTH_LONG, TastyToast.WARNING);
    }

    protected void showWarningToast2(String msg) {
        TastyToast.makeText(this, msg, Toast.LENGTH_SHORT, TastyToast.WARNING);
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
