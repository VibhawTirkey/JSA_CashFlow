package com.jsa.analytics.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.jsa.analytics.R;
import com.jsa.analytics.adapter.BannerAdapter;
import com.jsa.analytics.databinding.ActivityHomeScreenBinding;
import com.jsa.analytics.model.BannersModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class HomeScreenActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    ActivityHomeScreenBinding binding;
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    private List<BannersModel> bannerImgList = new ArrayList<>();
    private BannerAdapter bannerAdapter;
    private int currentPage = 2;
    private Timer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomeScreenBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        //toolbar
        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(this, binding.drawerLayout, binding.toolbar, R.string.drawer_open, R.string.drawer_close);
        drawerToggle.setDrawerIndicatorEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        drawerToggle.syncState();
        binding.navView.setNavigationItemSelectedListener(this);

        //banner
        getBannerData();
        bannerAdapter = new BannerAdapter(getApplicationContext(),bannerImgList);
        binding.bannerViewpager.setAdapter(bannerAdapter);
        binding.bannerViewpager.setClipToPadding(false);
        binding.bannerViewpager.setPadding(50,0,50,0);
        binding.bannerTab.setupWithViewPager(binding.bannerViewpager,true);
        binding.bannerViewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                currentPage = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                if (state == ViewPager.SCROLL_STATE_IDLE){
                    setBannerLooper();
                }
            }
        });
        startBannerSlider();
        binding.bannerViewpager.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                setBannerLooper();
                stopBannerSlider();
                if (motionEvent.getAction() == MotionEvent.ACTION_UP){
                    startBannerSlider();
                }
                return false;
            }
        });
    }

    private void getBannerData() {
        bannerImgList.add(new BannersModel("https://mir-s3-cdn-cf.behance.net/project_modules/1400/1a593e105730941.5f7f5c4db3a71.jpg"));
        bannerImgList.add(new BannersModel("https://blog.magezon.com/wp-content/uploads/2020/08/fashion-promotion-banner-sample.png"));
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.home:

                break;
            case R.id.profile:
                startActivity(new Intent(getApplicationContext(),ProfileActivity.class));
                break;
            case R.id.analytics:
                Toast.makeText(this, "Analytics", Toast.LENGTH_SHORT).show();
                break;
            case R.id.logout:
                firebaseAuth.signOut();
                startActivity(new Intent(getApplicationContext(),LoginActivity.class));
                finish();
                break;
            default:
                binding.drawerLayout.closeDrawer(GravityCompat.START);
        }
        binding.drawerLayout.closeDrawer(GravityCompat.START);
        return false;
    }

    private void setBannerLooper(){
        if (currentPage == bannerImgList.size()-2){
            currentPage = 2;
            binding.bannerViewpager.setCurrentItem(currentPage,false);
        }
        if (currentPage == 1){
            currentPage = bannerImgList.size()-3;
            binding.bannerViewpager.setCurrentItem(currentPage,false);
        }
    }

    private void startBannerSlider() {
        Handler handler = new Handler();
        Runnable update = () -> {
            if(currentPage >= bannerImgList.size()) {
                currentPage = 1;
            }
            binding.bannerViewpager.setCurrentItem(currentPage++,true);
        };

        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(update);
            }
        },2000,2000);
    }

    private void stopBannerSlider(){
        timer.cancel();
    }
}