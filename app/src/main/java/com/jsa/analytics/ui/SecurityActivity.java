package com.jsa.analytics.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.jsa.analytics.databinding.ActivitySecurityBinding;

public class SecurityActivity extends AppCompatActivity {

    ActivitySecurityBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySecurityBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.toolbar.setNavigationOnClickListener(v -> onBackPressed());

        binding.gotoTerms.setOnClickListener(v -> startActivity(new Intent(getApplicationContext(),TermAndConditionActivity.class)));

        binding.gotoPolicy.setOnClickListener(v -> startActivity(new Intent(getApplicationContext(),PrivacyPolicyActivity.class)));
    }
}