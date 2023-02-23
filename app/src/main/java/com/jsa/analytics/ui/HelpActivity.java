package com.jsa.analytics.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.view.View;

import com.jsa.analytics.adapter.HelpAdapter;
import com.jsa.analytics.databinding.ActivityHelpBinding;
import com.jsa.analytics.model.HelpModel;

import java.util.ArrayList;
import java.util.List;

public class HelpActivity extends AppCompatActivity {

    ActivityHelpBinding binding;
    HelpAdapter adapter;
    List<HelpModel> helpList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHelpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.toolbar.setNavigationOnClickListener(v -> onBackPressed());

        helpList = new ArrayList<>();
        helpList.add(new HelpModel("How would you handle my sensitive data?","Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer nec odio. Praesent libero. Sed cursus ante dapibus diam. Sed nisi. Nulla quis sem at nibh elementum imperdiet."));
        helpList.add(new HelpModel("We do not take your data. All working is done on your Computer through video calls.","Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer nec odio. Praesent libero. Sed cursus ante dapibus diam. Sed nisi. Nulla quis sem at nibh elementum imperdiet."));
        helpList.add(new HelpModel("I don’t know anything about finance. Will it work for me?","Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer nec odio. Praesent libero. Sed cursus ante dapibus diam. Sed nisi. Nulla quis sem at nibh elementum imperdiet."));
        helpList.add(new HelpModel("Can my team also join with me?","Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer nec odio. Praesent libero. Sed cursus ante dapibus diam. Sed nisi. Nulla quis sem at nibh elementum imperdiet."));

        adapter = new HelpAdapter(getApplicationContext(),helpList);
        binding.helpList.setLayoutManager(new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,false));
        binding.helpList.setAdapter(adapter);

    }
}