package com.jsa.analytics.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.gson.Gson;
import com.jsa.analytics.R;
import com.jsa.analytics.adapter.BannerAdapter;
import com.jsa.analytics.adapter.EndlessPagerAdapter;
import com.jsa.analytics.databinding.ActivityHomeScreenBinding;
import com.jsa.analytics.databinding.HomeNavHeaderLayoutBinding;
import com.jsa.analytics.model.BannerModel;
import com.jsa.analytics.model.BannersModel;
import com.jsa.analytics.model.FinancialSummaryModel;
import com.jsa.analytics.model.BalanceSheetModel;
import com.jsa.analytics.model.CashFlowModel;
import com.jsa.analytics.model.InputModel;
import com.jsa.analytics.utils.Utilities;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

public class HomeScreenActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private static final String TAG = "HomeData";
    ActivityHomeScreenBinding binding;
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    FirebaseFirestore firebaseFirestore;
    private List<BannersModel> bannerImgList = new ArrayList<>();
    private List<BannerModel> models = new ArrayList<>();
    private BannerAdapter bannerAdapter;
    private int currentPage = 1;
    private Timer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomeScreenBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        firebaseFirestore = FirebaseFirestore.getInstance();

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

        binding.bannerViewpager.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                stopBannerSlider();
                if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                    startBannerSlider();
                }
                return false;
            }
        });
        //banner

        //header
        headerView();
        //header

    }

    private void headerView() {
        View headerView = binding.navView.getHeaderView(0);
        HomeNavHeaderLayoutBinding headerLayoutBinding =HomeNavHeaderLayoutBinding.bind(headerView);
//        headerLayoutBinding.email.setText(firebaseUser.getEmail());
    }

    private void getBannerData() {
        firebaseFirestore.collection("learning").document("banner").get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()){
                    List<String> keys = new ArrayList<>();
                    JSONObject obj = new JSONObject(task.getResult().getData());
                    Iterator<String> iterator = obj.keys();
                    while(iterator.hasNext()){
                        String key = iterator.next();
                        keys.add(key);
                    }
                    for (String key:keys){
                        try {
                            JSONArray obj1 = obj.getJSONArray(key);
                            for (int i = 0;i<obj1.length();i++){
                                JSONObject object = obj1.getJSONObject(i);
                                BannerModel bannerModel = new Gson().fromJson(String.valueOf(object),BannerModel.class);
                                if (bannerModel.getVisible()){
                                    models.add(bannerModel);
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    bannerAdapter = new BannerAdapter(getApplicationContext(), models);
                    EndlessPagerAdapter endlessPagerAdapter = new EndlessPagerAdapter(bannerAdapter, binding.bannerViewpager);
                    binding.bannerViewpager.setAdapter(endlessPagerAdapter);
                    binding.bannerViewpager.setClipToPadding(false);
                    binding.bannerViewpager.setCurrentItem(currentPage, false);
                    binding.bannerViewpager.setPadding(50, 0, 50, 0);
                    binding.bannerTab.setupWithViewPager(binding.bannerViewpager, false);
                    startBannerSlider();
                }else {
                    Log.e(TAG, "onComplete: ",task.getException());
                }
            }
        });
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.videos:
                startActivity(new Intent(getApplicationContext(),VideoActivity.class));
                break;
            case R.id.profile:
                startActivity(new Intent(getApplicationContext(),ProfileActivity.class));
                break;
            case R.id.dashboard:
                startActivity(new Intent(getApplicationContext(), CashFlowDashboardActivity.class));
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

    private void startBannerSlider() {
        Handler handler = new Handler();
        Runnable update = () -> {
            if (binding.bannerViewpager.getCurrentItem()>=bannerImgList.size()){
                currentPage = 0;
            }
            binding.bannerViewpager.setCurrentItem(binding.bannerViewpager.getCurrentItem()+1,true);
        };

        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(update);
            }
        },6000,6000);
    }

    private void stopBannerSlider(){
        timer.cancel();
    }
}