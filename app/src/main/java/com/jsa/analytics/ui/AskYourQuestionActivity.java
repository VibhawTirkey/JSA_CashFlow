package com.jsa.analytics.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.jsa.analytics.R;
import com.jsa.analytics.adapter.AskYourQuestionAdapter;
import com.jsa.analytics.databinding.ActivityAskYourQuestionBinding;
import com.jsa.analytics.model.AskYourQuestionModel;

import java.util.ArrayList;
import java.util.List;

public class AskYourQuestionActivity extends AppCompatActivity {

    ActivityAskYourQuestionBinding binding;
    AskYourQuestionAdapter adapter;
    boolean oldQuestionPressed = true;

    List<AskYourQuestionModel> oldList;
    List<AskYourQuestionModel> newList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAskYourQuestionBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        oldList = new ArrayList<>();
        oldList.add(new AskYourQuestionModel("Purse Cash","Money Back Term Benefit with Profit Granted","view",true));
        oldList.add(new AskYourQuestionModel("Purse Cash","Single Premium Plan Without Profits","close",true));
        oldList.add(new AskYourQuestionModel("Purse Cash","Monthly Savings Assurance Plan With Profit","done",true));
        oldList.add(new AskYourQuestionModel("Purse Cash","Money Back Term Benefit with Profit Granted","view",true));
        oldList.add(new AskYourQuestionModel("Purse Cash","Single Premium Plan Without Profits","close",true));
        oldList.add(new AskYourQuestionModel("Purse Cash","Monthly Savings Assurance Plan With Profit","done",true));
        oldList.add(new AskYourQuestionModel("Purse Cash","Money Back Term Benefit with Profit Granted","view",true));
        oldList.add(new AskYourQuestionModel("Purse Cash","Single Premium Plan Without Profits","close",true));
        oldList.add(new AskYourQuestionModel("Purse Cash","Monthly Savings Assurance Plan With Profit","done",true));
        newList = new ArrayList<>();
        newList.add(new AskYourQuestionModel("Purse Cash","Money Back Term Benefit with Profit Granted","progress",true));
        newList.add(new AskYourQuestionModel("Purse Cash","Single Premium Plan Without Profits","progress",true));
        newList.add(new AskYourQuestionModel("Purse Cash","Monthly Savings Assurance Plan With Profit","progress",true));
        newList.add(new AskYourQuestionModel("Purse Cash","Money Back Term Benefit with Profit Granted","progress",true));
        newList.add(new AskYourQuestionModel("Purse Cash","Single Premium Plan Without Profits","progress",true));
        newList.add(new AskYourQuestionModel("Purse Cash","Monthly Savings Assurance Plan With Profit","progress",true));
        newList.add(new AskYourQuestionModel("Purse Cash","Money Back Term Benefit with Profit Granted","progress",true));
        newList.add(new AskYourQuestionModel("Purse Cash","Single Premium Plan Without Profits","progress",true));
        newList.add(new AskYourQuestionModel("Purse Cash","Monthly Savings Assurance Plan With Profit","progress",true));
        adapter = new AskYourQuestionAdapter(getApplicationContext(),oldList);
        binding.toolbar.setNavigationOnClickListener(v -> onBackPressed());
        binding.questionList.setLayoutManager(new LinearLayoutManager(getApplicationContext(), RecyclerView.VERTICAL,false));
        binding.questionList.setAdapter(adapter);

        binding.oldQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!oldQuestionPressed){
                    binding.oldQuestion.setBackgroundColor(getResources().getColor(R.color.toolbar_blue));
                    binding.oldQuestion.setTextColor(getResources().getColor(R.color.white));
                    binding.newQuestion.setTextColor(getResources().getColor(R.color.toolbar_blue));
                    binding.newQuestion.setBackgroundColor(getResources().getColor(R.color.white));
                    adapter = new AskYourQuestionAdapter(getApplicationContext(),oldList);
                    binding.questionList.setAdapter(adapter);
                    oldQuestionPressed = true;
                }
            }
        });

        binding.newQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (oldQuestionPressed){
                    binding.newQuestion.setBackgroundColor(getResources().getColor(R.color.toolbar_blue));
                    binding.newQuestion.setTextColor(getResources().getColor(R.color.white));
                    binding.oldQuestion.setTextColor(getResources().getColor(R.color.toolbar_blue));
                    binding.oldQuestion.setBackgroundColor(getResources().getColor(R.color.white));
                    adapter = new AskYourQuestionAdapter(getApplicationContext(),newList);
                    binding.questionList.setAdapter(adapter);
                    oldQuestionPressed = false;
                }
            }
        });
    }
}