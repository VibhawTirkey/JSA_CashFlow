package com.jsa.analytics.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;

import com.jsa.analytics.R;
import com.jsa.analytics.databinding.ActivityMonthSelectBinding;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MonthSelectActivity extends AppCompatActivity {

    ActivityMonthSelectBinding binding;
    String date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMonthSelectBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        binding.datePicker.findViewById(Resources.getSystem().getIdentifier("day", "id", "android")).setVisibility(View.GONE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            binding.datePicker.setOnDateChangedListener(new DatePicker.OnDateChangedListener() {
                @Override
                public void onDateChanged(DatePicker datePicker, int i, int i1, int i2) {
                    try {
                        Date month = new SimpleDateFormat("MM").parse(String.valueOf(i1+1));
                        binding.dateView.setText(new SimpleDateFormat("MMMM").format(month) +" "+i);
                        date = String.valueOf(i)+String.format("%02d",i1+1);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                }
            });
        }

        binding.gotoNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (date == null){
                    date = new SimpleDateFormat("yyyyMM").format(new Date());
                }
                Intent intent = new Intent(getApplicationContext(),FinancialSummaryActivity.class);
                intent.putExtra("date",date);
                startActivity(intent);
            }
        });

    }
}