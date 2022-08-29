package com.jsa.analytics.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.jsa.analytics.R;
import com.jsa.analytics.model.BannersModel;

import java.util.List;

public class BannerAdapter extends PagerAdapter {
    private Context context;
    private List<BannersModel> bannerItems;

    public BannerAdapter(Context context, List<BannersModel> bannerItems) {
        this.context = context;
        this.bannerItems = bannerItems;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = LayoutInflater.from(context).inflate(R.layout.banner_item_layout,container,false);
        ImageView imageView = view.findViewById(R.id.banner_img);
        Glide.with(context).load(bannerItems.get(position).getImgUrl())
//                .thumbnail(Glide.with(context).load(R.drawable.image_place_holder))
                .into(imageView);
        container.addView(view,0);
        return view;
    }

    @Override
    public int getCount() {
        return bannerItems.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
