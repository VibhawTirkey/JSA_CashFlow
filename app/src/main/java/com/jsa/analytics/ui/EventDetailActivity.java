package com.jsa.analytics.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.bumptech.glide.Glide;
import com.jsa.analytics.databinding.ActivityEventDetailBinding;

public class EventDetailActivity extends AppCompatActivity {

    ActivityEventDetailBinding binding;
    String title,date,detail,location,image;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityEventDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.toolbar.setNavigationOnClickListener(v -> onBackPressed());

        title = getIntent().getStringExtra("eventName");
        date = getIntent().getStringExtra("eventDate");
        detail = getIntent().getStringExtra("eventDetail");
        location = getIntent().getStringExtra("eventLocation");
        image = getIntent().getStringExtra("eventImage");

        binding.title.setText(title);
        binding.description.setText(detail);
        binding.date.setText(date);
        Glide.with(getApplicationContext()).load(image).into(binding.eventImage);
    }
}