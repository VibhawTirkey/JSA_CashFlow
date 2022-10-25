package com.jsa.analytics.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.jsa.analytics.adapter.ChartAdapter;
import com.jsa.analytics.databinding.ActivityAnalyticsOutputBinding;
import com.jsa.analytics.model.ChartModel;
import com.jsa.analytics.model.InputModel;
import com.jsa.analytics.utils.StaticFields;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AnalyticsOutputActivity extends AppCompatActivity {

    ActivityAnalyticsOutputBinding binding;
    FirebaseAuth firebaseAuth;
    FirebaseFirestore firebaseFirestore;
    InputModel inputModel;
    float gp,gpp;
    List<ChartModel> chartModels;
    ChartAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAnalyticsOutputBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();

        binding.toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });


        chartModels = new ArrayList<>();

        //calculation for chart
        Double currentAssetActual = StaticFields.inputChart.getBalanceSheet().getDebtors().getActualData()+
                StaticFields.inputChart.getBalanceSheet().getInventory().getActualData()+
                StaticFields.inputChart.getBalanceSheet().getOtherAsset().getActualData();
        Double currentAssetPlan = StaticFields.inputChart.getBalanceSheet().getDebtors().getExpectedData()+
                StaticFields.inputChart.getBalanceSheet().getInventory().getExpectedData()+
                StaticFields.inputChart.getBalanceSheet().getOtherAsset().getExpectedData();
        Double currentLiabilityActual =  StaticFields.inputChart.getBalanceSheet().getCreditors().getActualData()+
                StaticFields.inputChart.getBalanceSheet().getOtherLiabilities().getActualData();
        Double currentLiabilityPlan =  StaticFields.inputChart.getBalanceSheet().getCreditors().getExpectedData()+
                StaticFields.inputChart.getBalanceSheet().getOtherLiabilities().getExpectedData();
        Double debtorDaysActual = 365/StaticFields.inputChart.getFinancialSummary().getRevenue().getActualData()*StaticFields.inputChart.getBalanceSheet().getDebtors().getActualData();
        Double debtorDaysPLan =365/StaticFields.inputChart.getFinancialSummary().getRevenue().getExpectedData()*StaticFields.inputChart.getBalanceSheet().getDebtors().getExpectedData();
        Double inventoryDaysActual = 365/StaticFields.inputChart.getFinancialSummary().getTotalVariableCost().getActualData()*StaticFields.inputChart.getBalanceSheet().getInventory().getActualData();
        Double inventoryDaysPlan = 365/StaticFields.inputChart.getFinancialSummary().getTotalVariableCost().getExpectedData()*StaticFields.inputChart.getBalanceSheet().getInventory().getExpectedData();
        Double creditorDaysActual = 365/(StaticFields.inputChart.getFinancialSummary().getTotalVariableCost().getActualData()+
                StaticFields.inputChart.getBalanceSheet().getInventory().getActualData()-StaticFields.inputChart.getBalanceSheet().getInventory().getActualData())*
                StaticFields.inputChart.getBalanceSheet().getCreditors().getActualData();
        Double creditorDaysPlan = 365/(StaticFields.inputChart.getFinancialSummary().getTotalVariableCost().getExpectedData()+
                StaticFields.inputChart.getBalanceSheet().getInventory().getExpectedData()-StaticFields.inputChart.getBalanceSheet().getInventory().getExpectedData())*
                StaticFields.inputChart.getBalanceSheet().getCreditors().getExpectedData();


        //list of data for adapter
        chartModels.add(new ChartModel("Gross Profit Ratio", StaticFields.inputChart.getFinancialSummary().getGrossProfitRatio().getActualData(),
                StaticFields.inputChart.getFinancialSummary().getGrossProfitRatio().getExpectedData()));
        chartModels.add(new ChartModel("Net Profit Ratio", StaticFields.inputChart.getFinancialSummary().getNetProfitRatio().getActualData(),
                StaticFields.inputChart.getFinancialSummary().getNetProfitRatio().getExpectedData()));
        chartModels.add(new ChartModel("ROI", StaticFields.inputChart.getFinancialSummary().getNetProfit().getActualData()/StaticFields.inputChart.getBalanceSheet().getCapital().getActualData(),
                StaticFields.inputChart.getFinancialSummary().getNetProfit().getExpectedData()/StaticFields.inputChart.getBalanceSheet().getCapital().getExpectedData()));
        chartModels.add(new ChartModel("Inventory Turn", StaticFields.inputChart.getFinancialSummary().getTotalVariableCost().getActualData()/StaticFields.inputChart.getBalanceSheet().getInventory().getActualData(),
                StaticFields.inputChart.getFinancialSummary().getTotalVariableCost().getExpectedData()/StaticFields.inputChart.getBalanceSheet().getInventory().getExpectedData()));
        chartModels.add(new ChartModel("Current Ratio", currentAssetActual/currentLiabilityActual,
                currentAssetPlan/currentLiabilityPlan));
        chartModels.add(new ChartModel("Quick Ratio", (currentAssetActual-StaticFields.inputChart.getBalanceSheet().getInventory().getActualData())/currentLiabilityActual,
                (currentAssetPlan-StaticFields.inputChart.getBalanceSheet().getInventory().getExpectedData())/currentLiabilityPlan));
        chartModels.add(new ChartModel("Debtor Days", debtorDaysActual, debtorDaysPLan));
        chartModels.add(new ChartModel("Inventory Days",inventoryDaysActual , inventoryDaysPlan));
        chartModels.add(new ChartModel("Creditor Days", creditorDaysActual,creditorDaysPlan));
        chartModels.add(new ChartModel("Cash Conversion Cycle Days", debtorDaysActual+inventoryDaysActual-creditorDaysActual,
                debtorDaysPLan+inventoryDaysPlan-creditorDaysPlan));
        chartModels.add(new ChartModel("Working Capital", currentAssetActual-currentLiabilityActual,currentAssetPlan-currentLiabilityPlan));
        chartModels.add(new ChartModel("Debt Equity Ratio", StaticFields.inputChart.getBalanceSheet().getLoan().getActualData()/
                (StaticFields.inputChart.getBalanceSheet().getCapital().getActualData()+
                        StaticFields.inputChart.getBalanceSheet().getReservesAndSurplus().getActualData()),
                StaticFields.inputChart.getBalanceSheet().getLoan().getExpectedData()/
                        (StaticFields.inputChart.getBalanceSheet().getCapital().getExpectedData()+
                                StaticFields.inputChart.getBalanceSheet().getReservesAndSurplus().getExpectedData())));
        chartModels.add(new ChartModel("Sale", StaticFields.inputChart.getFinancialSummary().getRevenue().getActualData(),
                StaticFields.inputChart.getFinancialSummary().getRevenue().getExpectedData()));
        chartModels.add(new ChartModel("Gross Profit", StaticFields.inputChart.getFinancialSummary().getGrossProfit().getActualData(),
                StaticFields.inputChart.getFinancialSummary().getGrossProfit().getExpectedData()));
        chartModels.add(new ChartModel("Net Profit", StaticFields.inputChart.getFinancialSummary().getNetProfit().getActualData(),
                StaticFields.inputChart.getFinancialSummary().getNetProfit().getExpectedData()));


        //recyclerview declaration
        adapter = new ChartAdapter(getApplicationContext(),chartModels);
        binding.chartViews.setLayoutManager(new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,false));
        binding.chartViews.setAdapter(adapter);

    }


}