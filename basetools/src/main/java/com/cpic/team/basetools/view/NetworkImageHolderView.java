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
public class NetworkImageHolderView extends Holder<String> {
    private ImageView imageView;
    private Context context;

    public NetworkImageHolderView(View itemView) {
        super(itemView);
    }

    public NetworkImageHolderView(View itemView, Context context) {
        super(itemView);
        this.context = context;
    }

    @Override
    protected void initView(View itemView) {
        imageView = (ImageView) itemView;
    }

    @Override
    public void updateUI(String data) {
        imageView.setImageResource(R.drawable.empty_photo2);
        Picasso.with(context)
                .load(data)
                .placeholder(R.mipmap.jiazaitu)
                .error(R.drawable.empty_photo2)
                .centerCrop()
                .resize(500, 250)
                .into(imageView);
    }
}
