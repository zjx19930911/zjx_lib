package com.cpic.team.basetools.view;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.bigkoo.convenientbanner.holder.Holder;
import com.cpic.team.basetools.R;
import com.squareup.picasso.Picasso;


/**
 * Created by Taylor on 2016/8/19.
 */
public class NetworkImageHolderView implements Holder<String> {
    private ImageView imageView;
    @Override
    public View createView(Context context) {
        //你可以通过layout文件来创建，也可以像我一样用代码创建，不一定是Image，任何控件都可以进行翻页
        imageView = new ImageView(context);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        return imageView;
    }

    @Override
    public void UpdateUI(Context context, int position, String data) {
        imageView.setImageResource(R.drawable.empty_photo2);
        Picasso.with(context)
                .load(data)
                .placeholder(R.mipmap.jiazaitu)
                .error(R.drawable.empty_photo2)
                .centerCrop()
                .resize(500, 250)
                .into(imageView);
//        Glide.with(SampleApplicationContext.context).load(data).placeholder(R.drawable.empty_photo2).error(R.drawable.empty_photo2).into(imageView);
    }
}
