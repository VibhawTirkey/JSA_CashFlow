package com.jsa.analytics.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.jsa.analytics.R;
import com.jsa.analytics.databinding.ActivityFinancialSummaryBinding;
import com.jsa.analytics.model.FinancialSummaryModel;
import com.jsa.analytics.model.InputModel;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FinancialSummaryActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    ActivityFinancialSummaryBinding binding;
    String[] setPriceUnit = {"K","L","Cr"};
    FirebaseFirestore firebaseFirestore;
    FirebaseAuth firebaseAuth;
    InputModel inputModel = new InputModel();
    FinancialSummaryModel financialSummaryModel = new FinancialSummaryModel();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFinancialSummaryBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_round_arrow_back);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();
        String date = new SimpleDateFormat("yyyyMM").format(new Date());

        binding.toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        binding.gotoSecondInput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getInputData();
                Intent intent = new Intent(getApplicationContext(),BalanceSheetActivity.class);
                intent.putExtra("financialSummary",(Serializable)financialSummaryModel);
                startActivity(intent);
//                startActivity(new Intent(getApplicationContext(), BalanceSheetActivity.class));
                /*firebaseFirestore.collection("analyticsData").document(firebaseAuth.getUid()).update(date,inputModel).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){

                        }else {
                            Toast.makeText(FinancialSummaryActivity.this, task.getException().toString(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });*/

            }
        });

        ArrayAdapter<String> adapter = new ArrayAdapter<>(FinancialSummaryActivity.this, android.R.layout.simple_spinner_item, setPriceUnit);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.revenueContainer.planSpinner.setAdapter(adapter);
//        binding.creditorsDaysContainer.planSpinner.setAdapter(adapter);
//        binding.debtorsDaysContainer.planSpinner.setAdapter(adapter);
        binding.gpPercentContainer.planSpinner.setAdapter(adapter);
//        binding.newCapitalContainer.planSpinner.setAdapter(adapter);
        binding.npRatioContainer.planSpinner.setAdapter(adapter);
//        binding.inventoryDaysContainer.planSpinner.setAdapter(adapter);

//        binding.inventoryDaysContainer.actualSpinner.setAdapter(adapter);
        binding.revenueContainer.actualSpinner.setAdapter(adapter);
//        binding.creditorsDaysContainer.actualSpinner.setAdapter(adapter);
//        binding.debtorsDaysContainer.actualSpinner.setAdapter(adapter);
        binding.gpPercentContainer.actualSpinner.setAdapter(adapter);
//        binding.newCapitalContainer.actualSpinner.setAdapter(adapter);
        binding.npRatioContainer.actualSpinner.setAdapter(adapter);
        binding.revenueContainer.actualSpinner.setOnItemSelectedListener(this);
//        binding.revenueContainer.planInput.setCompoundDrawables(null,null,Utilities.getTextDrawable(getApplicationContext(), ContextCompat.getColor(getApplicationContext(),R.color.black),"₹"),null);

    }

    private void getInputData() {
        financialSummaryModel.setRevenue(new FinancialSummaryModel.Revenue(
                new FinancialSummaryModel.Plan(
                        Float.parseFloat(binding.revenueContainer.planInput.getText().toString().isEmpty()?"0":binding.revenueContainer.planInput.getText().toString())),
                new FinancialSummaryModel.Actual(
                        Float.parseFloat(binding.revenueContainer.actualInput.getText().toString().isEmpty()?"0":binding.revenueContainer.actualInput.getText().toString()))));
        financialSummaryModel.setTotalVariableCost(new FinancialSummaryModel.TotalVariableCost(
                new FinancialSummaryModel.Plan(
                        Float.parseFloat(binding.tvcContainer.planInput.getText().toString().isEmpty()?"0":binding.tvcContainer.planInput.getText().toString())),
                new FinancialSummaryModel.Actual(
                        Float.parseFloat(binding.tvcContainer.actualInput.getText().toString().isEmpty()?"0":binding.tvcContainer.actualInput.getText().toString()))));
        financialSummaryModel.setGrossProfit(new FinancialSummaryModel.GrossProfit(
                new FinancialSummaryModel.Plan(Float.parseFloat(binding.gpContainer.planInput.getText().toString().isEmpty()?"0":binding.gpContainer.planInput.getText().toString())),
                new FinancialSummaryModel.Actual(Float.parseFloat(binding.gpContainer.actualInput.getText().toString().isEmpty()?"0":binding.gpContainer.actualInput.getText().toString()))));
        financialSummaryModel.setGrossProfitRatio(new FinancialSummaryModel.GrossProfitRatio(
                new FinancialSummaryModel.Plan(Float.parseFloat(binding.gpPercentContainer.planInput.getText().toString().isEmpty()?"0":binding.gpPercentContainer.planInput.getText().toString())),
                new FinancialSummaryModel.Actual(Float.parseFloat(binding.gpPercentContainer.actualInput.getText().toString().isEmpty()?"0":binding.gpPercentContainer.actualInput.getText().toString()))));
        financialSummaryModel.setOtherIncome(new FinancialSummaryModel.OtherIncome(
                new FinancialSummaryModel.Plan(Float.parseFloat(binding.otherIncomeContainer.planInput.getText().toString().isEmpty()?"0":binding.otherIncomeContainer.planInput.getText().toString())),
                new FinancialSummaryModel.Actual(Float.parseFloat(binding.otherIncomeContainer.actualInput.getText().toString().isEmpty()?"0":binding.otherIncomeContainer.actualInput.getText().toString()))));
        financialSummaryModel.setFixedCost(new FinancialSummaryModel.FixedCost(
                new FinancialSummaryModel.Plan(Float.parseFloat(binding.fixedCostContainer.planInput.getText().toString().isEmpty()?"0":binding.fixedCostContainer.planInput.getText().toString())),
                new FinancialSummaryModel.Actual(Float.parseFloat(binding.fixedCostContainer.actualInput.getText().toString().isEmpty()?"0":binding.fixedCostContainer.actualInput.getText().toString()))));
        financialSummaryModel.setNetProfit(new FinancialSummaryModel.NetProfit(
                new FinancialSummaryModel.Plan(Float.parseFloat(binding.npContainer.planInput.getText().toString().isEmpty()?"0":binding.npContainer.planInput.getText().toString())),
                new FinancialSummaryModel.Actual(Float.parseFloat(binding.npContainer.actualInput.getText().toString().isEmpty()?"0":binding.npContainer.actualInput.getText().toString()))));
        financialSummaryModel.setNetProfitRatio(new FinancialSummaryModel.NetProfitRatio(
                new FinancialSummaryModel.Plan(Float.parseFloat(binding.npRatioContainer.planInput.getText().toString().isEmpty()?"0":binding.npRatioContainer.planInput.getText().toString())),
                new FinancialSummaryModel.Actual(Float.parseFloat(binding.npRatioContainer.actualInput.getText().toString().isEmpty()?"0":binding.npRatioContainer.actualInput.getText().toString()))));
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}