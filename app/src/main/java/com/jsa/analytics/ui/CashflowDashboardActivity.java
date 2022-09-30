package com.jsa.analytics.ui;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import com.jsa.analytics.databinding.ActivityCashflowDashboardBinding;

public class CashflowDashboardActivity extends AppCompatActivity {

    ActivityCashflowDashboardBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCashflowDashboardBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}