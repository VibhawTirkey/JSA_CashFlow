package com.jsa.analytics.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.jsa.analytics.adapter.HelpAdapter;
import com.jsa.analytics.databinding.ActivityHelpBinding;
import com.jsa.analytics.model.GuidanceModel;
import com.jsa.analytics.model.HelpModel;
import com.jsa.analytics.utils.Utilities;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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
        try {
            JSONObject obj = new JSONObject(Utilities.loadJSONFromAsset(HelpActivity.this, "JSA.json"));
            JSONArray jsonArray = obj.getJSONArray("help");

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jo_inside = jsonArray.getJSONObject(i);
                String title = jo_inside.getString("title");
                String description = jo_inside.getString("description");

                //Add your values in your `ArrayList` as below:
                helpList.add(new HelpModel(title,description));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        adapter = new HelpAdapter(getApplicationContext(),helpList);
        binding.helpList.setLayoutManager(new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,false));
        binding.helpList.setAdapter(adapter);

    }
}