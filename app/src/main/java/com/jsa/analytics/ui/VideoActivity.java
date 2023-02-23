package com.jsa.analytics.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.view.View;

import com.jsa.analytics.adapter.VideoAdapter;
import com.jsa.analytics.databinding.ActivityVideoBinding;

public class VideoActivity extends AppCompatActivity {

    ActivityVideoBinding binding;
    VideoAdapter videoAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityVideoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        videoAdapter = new VideoAdapter(getApplicationContext());
        binding.videoList.setLayoutManager(new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,false));
        binding.videoList.setAdapter(videoAdapter);
    }
}