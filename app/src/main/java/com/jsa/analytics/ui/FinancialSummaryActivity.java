package com.jsa.analytics.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
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
import com.jsa.analytics.utils.StaticFields;

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

        binding.toolbar.setNavigationOnClickListener(view -> onBackPressed());

        if (getIntent().getStringExtra("date")!=null){
            binding.gotoSecondInput.setOnClickListener(view -> {
                getInputData();
                StaticFields.financialSummaryModel = financialSummaryModel;
                Intent intent = new Intent(getApplicationContext(),BalanceSheetActivity.class);
                intent.putExtra("date",getIntent().getStringExtra("date"));
                startActivity(intent);
            });
        } else {
            setDataOnFields(StaticFields.editModel);
            binding.gotoSecondInput.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    getInputData();
                    StaticFields.editModel.setFinancialSummary(financialSummaryModel);
                    Intent intent = new Intent(getApplicationContext(),BalanceSheetActivity.class);
                    intent.putExtra("newData",inputModel);
                    intent.putExtra("editDate",getIntent().getStringExtra("editDate"));
                    startActivity(intent);
                }
            });

            ArrayAdapter<String> adapter = new ArrayAdapter<>(FinancialSummaryActivity.this, android.R.layout.simple_spinner_item, setPriceUnit);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            binding.revenue.planSpinner.setAdapter(adapter);
//        binding.creditorsDaysContainer.planSpinner.setAdapter(adapter);
//        binding.debtorsDaysContainer.planSpinner.setAdapter(adapter);
            binding.gpPercent.planSpinner.setAdapter(adapter);
//        binding.newCapitalContainer.planSpinner.setAdapter(adapter);
            binding.npPercent.planSpinner.setAdapter(adapter);
//        binding.inventoryDaysContainer.planSpinner.setAdapter(adapter);

//        binding.inventoryDaysContainer.actualSpinner.setAdapter(adapter);
            binding.revenue.actualSpinner.setAdapter(adapter);
//        binding.creditorsDaysContainer.actualSpinner.setAdapter(adapter);
//        binding.debtorsDaysContainer.actualSpinner.setAdapter(adapter);
            binding.gpPercent.actualSpinner.setAdapter(adapter);
//        binding.newCapitalContainer.actualSpinner.setAdapter(adapter);
            binding.npPercent.actualSpinner.setAdapter(adapter);
            binding.revenue.actualSpinner.setOnItemSelectedListener(this);
//        binding.revenue.planInput.setCompoundDrawables(null,null,Utilities.getTextDrawable(getApplicationContext(), ContextCompat.getColor(getApplicationContext(),R.color.black),"₹"),null);
        }

    }

    private void setDataOnFields(InputModel model) {
        binding.revenue.actualInput.setText(String.valueOf(StaticFields.editModel.getFinancialSummary().getRevenue().getActualData()));
        binding.revenue.planInput.setText(String.valueOf(StaticFields.editModel.getFinancialSummary().getRevenue().getExpectedData()));
        binding.tvc.actualInput.setText(String.valueOf(StaticFields.editModel.getFinancialSummary().getTotalVariableCost().getActualData()));
        binding.tvc.planInput.setText(String.valueOf(StaticFields.editModel.getFinancialSummary().getTotalVariableCost().getExpectedData()));
        binding.grossProfit.actualInput.setText(String.valueOf(StaticFields.editModel.getFinancialSummary().getGrossProfit().getActualData()));
        binding.grossProfit.planInput.setText(String.valueOf(StaticFields.editModel.getFinancialSummary().getGrossProfit().getExpectedData()));
        binding.gpPercent.actualInput.setText(String.valueOf(StaticFields.editModel.getFinancialSummary().getGrossProfitRatio().getActualData()));
        binding.gpPercent.planInput.setText(String.valueOf(StaticFields.editModel.getFinancialSummary().getGrossProfitRatio().getExpectedData()));
        binding.otherIncome.actualInput.setText(String.valueOf(StaticFields.editModel.getFinancialSummary().getOtherIncome().getActualData()));
        binding.otherIncome.planInput.setText(String.valueOf(StaticFields.editModel.getFinancialSummary().getOtherIncome().getExpectedData()));
        binding.fixedCost.actualInput.setText(String.valueOf(StaticFields.editModel.getFinancialSummary().getFixedCost().getActualData()));
        binding.fixedCost.planInput.setText(String.valueOf(StaticFields.editModel.getFinancialSummary().getFixedCost().getExpectedData()));
        binding.netProfit.actualInput.setText(String.valueOf(StaticFields.editModel.getFinancialSummary().getNetProfit().getActualData()));
        binding.netProfit.planInput.setText(String.valueOf(StaticFields.editModel.getFinancialSummary().getNetProfit().getExpectedData()));
        binding.npPercent.actualInput.setText(String.valueOf(StaticFields.editModel.getFinancialSummary().getNetProfitRatio().getActualData()));
        binding.npPercent.planInput.setText(String.valueOf(StaticFields.editModel.getFinancialSummary().getNetProfitRatio().getExpectedData()));
    }

    private void getInputData() {
        financialSummaryModel.setRevenue(new FinancialSummaryModel.Revenue(
                Double.parseDouble(binding.revenue.actualInput.getText().toString().isEmpty()?"0":binding.revenue.actualInput.getText().toString()),
                Double.parseDouble(binding.revenue.planInput.getText().toString().isEmpty()?"0":binding.revenue.planInput.getText().toString()),
                "crore"));
        financialSummaryModel.setTotalVariableCost(new FinancialSummaryModel.TotalVariableCost(
                Double.parseDouble(binding.tvc.actualInput.getText().toString().isEmpty()?"0":binding.tvc.actualInput.getText().toString()),
                Double.parseDouble(binding.tvc.planInput.getText().toString().isEmpty()?"0":binding.tvc.planInput.getText().toString()),
                "crore"));
        financialSummaryModel.setGrossProfit(new FinancialSummaryModel.GrossProfit(
                Double.parseDouble(binding.grossProfit.actualInput.getText().toString().isEmpty()?"0":binding.grossProfit.actualInput.getText().toString()),
                Double.parseDouble(binding.grossProfit.planInput.getText().toString().isEmpty()?"0":binding.grossProfit.planInput.getText().toString()),
                "crore"));
        financialSummaryModel.setGrossProfitRatio(new FinancialSummaryModel.GrossProfitRatio(
                Double.parseDouble(binding.gpPercent.actualInput.getText().toString().isEmpty()?"0":binding.gpPercent.actualInput.getText().toString()),
                Double.parseDouble(binding.gpPercent.planInput.getText().toString().isEmpty()?"0":binding.gpPercent.planInput.getText().toString()),
                "crore"));
        financialSummaryModel.setOtherIncome(new FinancialSummaryModel.OtherIncome(
                Double.parseDouble(binding.otherIncome.actualInput.getText().toString().isEmpty()?"0":binding.otherIncome.actualInput.getText().toString()),
                Double.parseDouble(binding.otherIncome.planInput.getText().toString().isEmpty()?"0":binding.otherIncome.planInput.getText().toString()),
                "crore"));
        financialSummaryModel.setFixedCost(new FinancialSummaryModel.FixedCost(
                Double.parseDouble(binding.fixedCost.actualInput.getText().toString().isEmpty()?"0":binding.fixedCost.actualInput.getText().toString()),
                Double.parseDouble(binding.fixedCost.planInput.getText().toString().isEmpty()?"0":binding.fixedCost.planInput.getText().toString()),
                "crore"));
        financialSummaryModel.setNetProfit(new FinancialSummaryModel.NetProfit(
                Double.parseDouble(binding.netProfit.actualInput.getText().toString().isEmpty()?"0":binding.netProfit.actualInput.getText().toString()),
                Double.parseDouble(binding.netProfit.planInput.getText().toString().isEmpty()?"0":binding.netProfit.planInput.getText().toString()),
                "crore"));
        financialSummaryModel.setNetProfitRatio(new FinancialSummaryModel.NetProfitRatio(
                Double.parseDouble(binding.npPercent.actualInput.getText().toString().isEmpty()?"0":binding.npPercent.actualInput.getText().toString()),
                Double.parseDouble(binding.npPercent.planInput.getText().toString().isEmpty()?"0":binding.npPercent.planInput.getText().toString()),
                "crore"));

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}