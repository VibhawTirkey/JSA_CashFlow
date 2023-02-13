package com.jsa.analytics.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.jsa.analytics.ui.fragment.ContactFragment;
import com.jsa.analytics.ui.fragment.NotificationFragment;

public class InsightAdapter extends FragmentPagerAdapter {

    public InsightAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        if (position == 0 ){
            fragment = new NotificationFragment();
        }else if (position == 1){
            fragment = new ContactFragment();
        }
        return fragment;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        String title = null;
        if (position == 0){
            title = "Articles";
        }else if (position==1){
            title = "Events";
        }
        return title;
    }

    @Override
    public int getCount() {
        return 2;
    }
}
