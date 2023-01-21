package com.jsa.analytics.ui;

import static androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_NO;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.jsa.analytics.R;
import com.jsa.analytics.databinding.ActivityDashboardBinding;
import com.jsa.analytics.ui.fragment.HomeFragment;
import com.jsa.analytics.ui.fragment.InsightFragment;

public class DashboardActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener{

    ActivityDashboardBinding binding;
    FragmentTransaction transaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCompatDelegate.setDefaultNightMode(MODE_NIGHT_NO);
        binding = ActivityDashboardBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

//        getSupportActionBar().hide();
        transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(binding.homeContainer.getId(),new HomeFragment());
        transaction.commit();
        binding.bottomNav.setOnNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.home:
                transaction = getSupportFragmentManager().beginTransaction();
                transaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out);
                transaction.replace(binding.homeContainer.getId(),new HomeFragment());
                transaction.commit();
                return true;
            case R.id.insight:
                transaction = getSupportFragmentManager().beginTransaction();
                transaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out);
                transaction.replace(binding.homeContainer.getId(),new InsightFragment());
                transaction.commit();
                return true;
            case R.id.event:
                Toast.makeText(this, "Event", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.profile:
                Toast.makeText(this, "Profile", Toast.LENGTH_SHORT).show();
                return true;
            default:
                Toast.makeText(this, "default", Toast.LENGTH_SHORT).show();
        }
        return false;
    }
}