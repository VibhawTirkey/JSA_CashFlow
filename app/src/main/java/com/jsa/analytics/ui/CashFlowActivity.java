package com.jsa.analytics.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.jsa.analytics.R;
import com.jsa.analytics.databinding.ActivityCashFlowBinding;
import com.jsa.analytics.model.BalanceSheetModel;
import com.jsa.analytics.model.CashFlowModel;
import com.jsa.analytics.model.InputModel;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CashFlowActivity extends AppCompatActivity {

    ActivityCashFlowBinding binding;
    InputModel inputModel;
    CashFlowModel cashFlowModel;
    FirebaseAuth firebaseAuth;
    FirebaseFirestore firebaseFirestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCashFlowBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_round_arrow_back);
        binding.toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();
        inputModel = new InputModel();
        cashFlowModel = new CashFlowModel();
        String date = new SimpleDateFormat("yyyyMM").format(new Date());

        binding.gotoResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getInputData();
                inputModel.setCashFlow(cashFlowModel);
                firebaseFirestore.collection("analyticsData").document(firebaseAuth.getUid()).update(date,inputModel).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){
                            startActivity(new Intent(getApplicationContext(), AnalyticsOutputActivity.class));
                        }else {
                            Toast.makeText(CashFlowActivity.this, task.getException().toString(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                startActivity(new Intent(getApplicationContext(),AnalyticsOutputActivity.class));
            }
        });


    }

    private void getInputData() {
        cashFlowModel.setCollectionFromCustomer(new CashFlowModel.CollectionFromCustomer(
                new CashFlowModel.Plan(Float.parseFloat(binding.collectionFromCustomer.planInput.getText().toString().isEmpty()?"0":binding.collectionFromCustomer.planInput.getText().toString())),
                new CashFlowModel.Actual(Float.parseFloat(binding.collectionFromCustomer.actualInput.getText().toString().isEmpty()?"0":binding.collectionFromCustomer.actualInput.getText().toString()))));
        cashFlowModel.setCapitalIntroduce(new CashFlowModel.CapitalIntroduce(
                new CashFlowModel.Plan(Float.parseFloat(binding.capitalIntroduce.planInput.getText().toString().isEmpty()?"0":binding.capitalIntroduce.planInput.getText().toString())),
                new CashFlowModel.Actual(Float.parseFloat(binding.capitalIntroduce.actualInput.getText().toString().isEmpty()?"0":binding.capitalIntroduce.actualInput.getText().toString()))));
        cashFlowModel.setLoanTaken(new CashFlowModel.LoanTaken(
                new CashFlowModel.Plan(Float.parseFloat(binding.loanTaken.planInput.getText().toString().isEmpty()?"0":binding.loanTaken.planInput.getText().toString())),
                new CashFlowModel.Actual(Float.parseFloat(binding.loanTaken.actualInput.getText().toString().isEmpty()?"0":binding.loanTaken.actualInput.getText().toString()))));
        cashFlowModel.setFixedAssetSold(new CashFlowModel.FixedAssetSold(
                new CashFlowModel.Plan(Float.parseFloat(binding.fixedAssetSold.planInput.getText().toString().isEmpty()?"0":binding.fixedAssetSold.planInput.getText().toString())),
                new CashFlowModel.Actual(Float.parseFloat(binding.fixedAssetSold.actualInput.getText().toString().isEmpty()?"0":binding.fixedAssetSold.actualInput.getText().toString()))));
        cashFlowModel.setInvestmentSold(new CashFlowModel.InvestmentSold(
                new CashFlowModel.Plan(Float.parseFloat(binding.investmentSold.planInput.getText().toString().isEmpty()?"0":binding.investmentSold.planInput.getText().toString())),
                new CashFlowModel.Actual(Float.parseFloat(binding.investmentSold.actualInput.getText().toString().isEmpty()?"0":binding.investmentSold.actualInput.getText().toString()))));
        cashFlowModel.setVariableCost(new CashFlowModel.VariableCost(
                new CashFlowModel.Plan(Float.parseFloat(binding.variableCost.planInput.getText().toString().isEmpty()?"0":binding.variableCost.planInput.getText().toString())),
                new CashFlowModel.Actual(Float.parseFloat(binding.variableCost.actualInput.getText().toString().isEmpty()?"0":binding.variableCost.actualInput.getText().toString()))));
        cashFlowModel.setCapitalWithdrawal(new CashFlowModel.CapitalWithdrawal(
                new CashFlowModel.Plan(Float.parseFloat(binding.capitalWithdrawal.planInput.getText().toString().isEmpty()?"0":binding.capitalWithdrawal.planInput.getText().toString())),
                new CashFlowModel.Actual(Float.parseFloat(binding.capitalWithdrawal.actualInput.getText().toString().isEmpty()?"0":binding.capitalWithdrawal.actualInput.getText().toString()))));
        cashFlowModel.setLoanPayment(new CashFlowModel.LoanPayment(
                new CashFlowModel.Plan(Float.parseFloat(binding.loanPayment.planInput.getText().toString().isEmpty()?"0":binding.loanPayment.planInput.getText().toString())),
                new CashFlowModel.Actual(Float.parseFloat(binding.loanPayment.actualInput.getText().toString().isEmpty()?"0":binding.loanPayment.actualInput.getText().toString()))));
        cashFlowModel.setFixedAssetsBuy(new CashFlowModel.FixedAssetsBuy(
                new CashFlowModel.Plan(Float.parseFloat(binding.fixedAssetBuy.planInput.getText().toString().isEmpty()?"0":binding.fixedAssetBuy.planInput.getText().toString())),
                new CashFlowModel.Actual(Float.parseFloat(binding.fixedAssetBuy.actualInput.getText().toString().isEmpty()?"0":binding.fixedAssetBuy.actualInput.getText().toString()))));
        cashFlowModel.setInvestmentBuy(new CashFlowModel.InvestmentBuy(
                new CashFlowModel.Plan(Float.parseFloat(binding.investmentBuy.planInput.getText().toString().isEmpty()?"0":binding.investmentBuy.planInput.getText().toString())),
                new CashFlowModel.Actual(Float.parseFloat(binding.investmentBuy.actualInput.getText().toString().isEmpty()?"0":binding.investmentBuy.actualInput.getText().toString()))));
    }
}