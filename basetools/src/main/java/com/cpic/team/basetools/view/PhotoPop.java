package com.cpic.team.basetools.view;

import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.cpic.team.basetools.R;

import java.util.ArrayList;

import uk.co.senab.photoview.PhotoView;


/**
 * Created by Taylor on 2016/7/14.
 */
public class PhotoPop {

    public final static void showPhoto(final Activity activity , final int current_page, ArrayList<String> imgs){
        final PopupWindow pw;
        ViewPager viewPager;
        final TextView tvSelect,tvCount;
        ImageView ivDel;
        View view = View.inflate(activity, R.layout.activity_lookphoto,null);
        pw = new PopupWindow(view, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        pw.setFocusable(true);
        WindowManager.LayoutParams params = activity.getWindow().getAttributes();
        params.alpha = 0.0f;
        activity.getWindow().setAttributes(params);
        viewPager = (ViewPager) view.findViewById(R.id.photoview_pager);
        tvSelect = (TextView) view.findViewById(R.id.photoview_tvseclect);
        tvCount = (TextView) view.findViewById(R.id.photoview_tvall);
        ivDel = (ImageView) view.findViewById(R.id.photoview_ivdel);
        pw.setBackgroundDrawable(new ColorDrawable());
        pw.setOutsideTouchable(true);
        pw.showAtLocation(view, Gravity.CENTER, 0,0);

        pw.setOnDismissListener(new PopupWindow.OnDismissListener() {

            @Override
            public void onDismiss() {
                WindowManager.LayoutParams params = activity.getWindow().getAttributes();
                params.alpha = 1f;
                activity.getWindow().setAttributes(params);
            }
        });
        viewPager.setAdapter(new SamplePagerAdapter(activity,imgs));
        viewPager.setCurrentItem(current_page);
        tvSelect.setText(current_page+1+"");
        tvCount.setText(imgs.size()+"");
        ivDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pw.dismiss();
            }
        });

        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                tvSelect.setText(position+1+"");
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


    }
    static class SamplePagerAdapter extends PagerAdapter {
        Activity activity;
        ArrayList<String> imgs;


        SamplePagerAdapter(Activity activity, ArrayList<String> imgs){
            this.activity = activity;
            this.imgs = imgs;
        }

        @Override
        public int getCount() {
            return imgs.size();
        }

        @Override
        public View instantiateItem(ViewGroup container, int position) {
            PhotoView photoView = new PhotoView(container.getContext());
            Glide.with(activity).load(imgs.get(position)).into(photoView);
            // Now just add PhotoView to ViewPager and return it
            container.addView(photoView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            return photoView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

    }
}
