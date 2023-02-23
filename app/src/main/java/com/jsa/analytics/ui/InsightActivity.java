package com.jsa.analytics.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.jsa.analytics.adapter.InsightAdapter;
import com.jsa.analytics.databinding.ActivityInsightBinding;

public class InsightActivity extends AppCompatActivity {

    ActivityInsightBinding binding;
    InsightAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityInsightBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        adapter = new InsightAdapter(getSupportFragmentManager());
        binding.viewPager.setAdapter(adapter);
        binding.tabLayout.setupWithViewPager(binding.viewPager);
    }
}