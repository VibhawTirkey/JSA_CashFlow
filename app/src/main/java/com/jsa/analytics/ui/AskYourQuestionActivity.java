package com.jsa.analytics.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.jsa.analytics.databinding.ActivityAskYourQuestionBinding;

public class AskYourQuestionActivity extends AppCompatActivity {

    ActivityAskYourQuestionBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAskYourQuestionBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.toolbar.setNavigationOnClickListener(v -> onBackPressed());
        binding.gotoChat.setOnClickListener(v -> startActivity(new Intent(getApplicationContext(),ChatActivity.class)));
    }
}