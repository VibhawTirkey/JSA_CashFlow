package com.jsa.analytics.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.jsa.analytics.R;
import com.jsa.analytics.databinding.ActivityDashboardBinding;
import com.jsa.analytics.ui.fragment.HomeFragment;

public class DashboardActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener{

    ActivityDashboardBinding binding;
    FragmentTransaction transaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDashboardBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getSupportActionBar().hide();
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
                transaction.replace(binding.homeContainer.getId(),new HomeFragment());
                transaction.commit();
                Toast.makeText(this, "Home", Toast.LENGTH_SHORT).show();
                break;
            case R.id.insight:
                Toast.makeText(this, "Insight", Toast.LENGTH_SHORT).show();
                break;
            case R.id.event:
                Toast.makeText(this, "Event", Toast.LENGTH_SHORT).show();
                break;
            case R.id.profile:
                Toast.makeText(this, "Profile", Toast.LENGTH_SHORT).show();
                break;
            default:
                Toast.makeText(this, "default", Toast.LENGTH_SHORT).show();
        }
        return false;
    }
}