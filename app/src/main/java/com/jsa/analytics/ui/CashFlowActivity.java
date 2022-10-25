package com.jsa.analytics.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;
import com.jsa.analytics.R;
import com.jsa.analytics.databinding.ActivityCashFlowBinding;
import com.jsa.analytics.model.BalanceSheetModel;
import com.jsa.analytics.model.CashFlowModel;
import com.jsa.analytics.model.InputModel;
import com.jsa.analytics.utils.StaticFields;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class CashFlowActivity extends AppCompatActivity {

    private String TAG = "Upload";
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

        if (getIntent().getStringExtra("date")!=null){
            binding.gotoResult.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    getInputData();
                    StaticFields.cashFlowModel = cashFlowModel;
                    InputModel model = new InputModel();
                    model.setFinancialSummary(StaticFields.financialSummaryModel);
                    model.setBalanceSheet(StaticFields.balanceSheetModel);
                    model.setCashFlow(StaticFields.cashFlowModel);
                    Map<String,InputModel> map = new HashMap<String,InputModel>();
                    map.put(getIntent().getStringExtra("date"),model);

                    firebaseFirestore.collection("analyticsData").document(firebaseAuth.getUid()).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                            if (task.isSuccessful()){
                                DocumentSnapshot document = task.getResult();
                                if (document.exists()){
                                    firebaseFirestore.collection("analyticsData").document(firebaseAuth.getUid()).update(getIntent().getStringExtra("date"),model).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()){
                                                StaticFields.financialSummaryModel = null;
                                                StaticFields.balanceSheetModel =null;
                                                StaticFields.cashFlowModel = null;
                                                startActivity(new Intent(getApplicationContext(),CashFlowDashboardActivity.class));
                                                finishAffinity();
                                            }else {
                                            }
                                        }
                                    });

                                }else {
                                    firebaseFirestore.collection("analyticsData").document(firebaseAuth.getUid()).set(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()){
                                                StaticFields.financialSummaryModel = null;
                                                StaticFields.balanceSheetModel =null;
                                                StaticFields.cashFlowModel = null;
                                                startActivity(new Intent(getApplicationContext(),CashFlowDashboardActivity.class));
                                                finishAffinity();
                                            }else {
                                                Toast.makeText(CashFlowActivity.this, "Unable to Upload", Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    });
                                }
                            }
                        }
                    });

                }
            });
        }else {
            setDataOnFields(StaticFields.editModel);
            binding.gotoResult.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    getInputData();
                    inputModel.setCashFlow(cashFlowModel);
                    inputModel.setFinancialSummary(StaticFields.editModel.getFinancialSummary());
                    inputModel.setBalanceSheet(StaticFields.editModel.getBalanceSheet());
                    firebaseFirestore.collection("analyticsData").document(firebaseAuth.getUid()).update(getIntent().getStringExtra("editDate"),inputModel).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()){
                                startActivity(new Intent(getApplicationContext(),CashFlowDashboardActivity.class));
                                finishAffinity();
                            }else {
                                Toast.makeText(CashFlowActivity.this, task.getException().toString(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            });
        }
    }

    private void setDataOnFields(InputModel editModel) {
        binding.collectionFromCustomer.actualInput.setText(String.valueOf(editModel.getCashFlow().getCollectionFromCustomer().getActualData()));
        binding.collectionFromCustomer.planInput.setText(String.valueOf(editModel.getCashFlow().getCollectionFromCustomer().getExpectedData()));
        binding.capitalIntroduce.actualInput.setText(String.valueOf(editModel.getCashFlow().getCapitalIntroduce().getActualData()));
        binding.capitalIntroduce.planInput.setText(String.valueOf(editModel.getCashFlow().getCapitalIntroduce().getExpectedData()));
        binding.loanTaken.actualInput.setText(String.valueOf(editModel.getCashFlow().getLoanTaken().getActualData()));
        binding.loanTaken.planInput.setText(String.valueOf(editModel.getCashFlow().getLoanTaken().getExpectedData()));
        binding.fixedAssetSold.actualInput.setText(String.valueOf(editModel.getCashFlow().getFixedAssetSold().getActualData()));
        binding.fixedAssetSold.planInput.setText(String.valueOf(editModel.getCashFlow().getFixedAssetSold().getExpectedData()));
        binding.investmentSold.actualInput.setText(String.valueOf(editModel.getCashFlow().getInvestmentSold().getActualData()));
        binding.investmentSold.planInput.setText(String.valueOf(editModel.getCashFlow().getInvestmentSold().getExpectedData()));
        binding.variableCost.actualInput.setText(String.valueOf(editModel.getCashFlow().getVariableCost().getActualData()));
        binding.variableCost.planInput.setText(String.valueOf(editModel.getCashFlow().getVariableCost().getExpectedData()));
        binding.capitalWithdrawal.actualInput.setText(String.valueOf(editModel.getCashFlow().getCapitalWithdrawal().getActualData()));
        binding.capitalWithdrawal.planInput.setText(String.valueOf(editModel.getCashFlow().getCapitalWithdrawal().getExpectedData()));
        binding.loanPayment.actualInput.setText(String.valueOf(editModel.getCashFlow().getLoanPayment().getActualData()));
        binding.loanPayment.planInput.setText(String.valueOf(editModel.getCashFlow().getLoanPayment().getExpectedData()));
        binding.fixedAssetBuy.actualInput.setText(String.valueOf(editModel.getCashFlow().getFixedAssetsBuy().getActualData()));
        binding.fixedAssetBuy.planInput.setText(String.valueOf(editModel.getCashFlow().getFixedAssetsBuy().getExpectedData()));
        binding.investmentBuy.actualInput.setText(String.valueOf(editModel.getCashFlow().getInvestmentBuy().getActualData()));
        binding.investmentBuy.planInput.setText(String.valueOf(editModel.getCashFlow().getInvestmentBuy().getExpectedData()));

    }

    private void getInputData() {
        cashFlowModel.setCollectionFromCustomer(new CashFlowModel.CollectionFromCustomer(
                Double.parseDouble(binding.collectionFromCustomer.actualInput.getText().toString().isEmpty()?"0":binding.collectionFromCustomer.actualInput.getText().toString()),
                Double.parseDouble(binding.collectionFromCustomer.planInput.getText().toString().isEmpty()?"0":binding.collectionFromCustomer.planInput.getText().toString()),
                "crore"));
        cashFlowModel.setCapitalIntroduce(new CashFlowModel.CapitalIntroduce(
                Double.parseDouble(binding.capitalIntroduce.actualInput.getText().toString().isEmpty()?"0":binding.capitalIntroduce.actualInput.getText().toString()),
                Double.parseDouble(binding.capitalIntroduce.planInput.getText().toString().isEmpty()?"0":binding.capitalIntroduce.planInput.getText().toString()),
                "crore"));
        cashFlowModel.setLoanTaken(new CashFlowModel.LoanTaken(
                Double.parseDouble(binding.loanTaken.actualInput.getText().toString().isEmpty()?"0":binding.loanTaken.actualInput.getText().toString()),
                Double.parseDouble(binding.loanTaken.planInput.getText().toString().isEmpty()?"0":binding.loanTaken.planInput.getText().toString()),
                "crore"));
        cashFlowModel.setFixedAssetSold(new CashFlowModel.FixedAssetSold(
                Double.parseDouble(binding.fixedAssetSold.actualInput.getText().toString().isEmpty()?"0":binding.fixedAssetSold.actualInput.getText().toString()),
                Double.parseDouble(binding.fixedAssetSold.planInput.getText().toString().isEmpty()?"0":binding.fixedAssetSold.planInput.getText().toString()),
                "crore"));
        cashFlowModel.setInvestmentSold(new CashFlowModel.InvestmentSold(
                Double.parseDouble(binding.investmentSold.actualInput.getText().toString().isEmpty()?"0":binding.investmentSold.actualInput.getText().toString()),
                Double.parseDouble(binding.investmentSold.planInput.getText().toString().isEmpty()?"0":binding.investmentSold.planInput.getText().toString()),
                "crore"));
        cashFlowModel.setVariableCost(new CashFlowModel.VariableCost(
                Double.parseDouble(binding.variableCost.actualInput.getText().toString().isEmpty()?"0":binding.variableCost.actualInput.getText().toString()),
                Double.parseDouble(binding.variableCost.planInput.getText().toString().isEmpty()?"0":binding.variableCost.planInput.getText().toString()),
                "crore"));
        cashFlowModel.setCapitalWithdrawal(new CashFlowModel.CapitalWithdrawal(
                Double.parseDouble(binding.capitalWithdrawal.actualInput.getText().toString().isEmpty()?"0":binding.capitalWithdrawal.actualInput.getText().toString()),
                Double.parseDouble(binding.capitalWithdrawal.planInput.getText().toString().isEmpty()?"0":binding.capitalWithdrawal.planInput.getText().toString()),
                "crore"));
        cashFlowModel.setLoanPayment(new CashFlowModel.LoanPayment(
                Double.parseDouble(binding.loanPayment.actualInput.getText().toString().isEmpty()?"0":binding.loanPayment.actualInput.getText().toString()),
                Double.parseDouble(binding.loanPayment.planInput.getText().toString().isEmpty()?"0":binding.loanPayment.planInput.getText().toString()),
                "crore"));
        cashFlowModel.setFixedAssetsBuy(new CashFlowModel.FixedAssetsBuy(
                Double.parseDouble(binding.fixedAssetBuy.actualInput.getText().toString().isEmpty()?"0":binding.fixedAssetBuy.actualInput.getText().toString()),
                Double.parseDouble(binding.fixedAssetBuy.planInput.getText().toString().isEmpty()?"0":binding.fixedAssetBuy.planInput.getText().toString()),
                "crore"));
        cashFlowModel.setInvestmentBuy(new CashFlowModel.InvestmentBuy(
                Double.parseDouble(binding.investmentBuy.actualInput.getText().toString().isEmpty()?"0":binding.investmentBuy.actualInput.getText().toString()),
                Double.parseDouble(binding.investmentBuy.planInput.getText().toString().isEmpty()?"0":binding.investmentBuy.planInput.getText().toString()),
                "crore"));
    }
}