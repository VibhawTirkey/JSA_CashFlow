package com.jsa.analytics.ui.fragment;

import static android.app.Activity.RESULT_OK;
import static com.jsa.analytics.utils.Constants.OPEN_DRAWER;
import static com.jsa.analytics.utils.Constants.OPEN_NOTIFICATION;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.jsa.analytics.R;
import com.jsa.analytics.callback.IFragmentCallback;
import com.jsa.analytics.databinding.AddDialogFragmentLayoutBinding;
import com.jsa.analytics.databinding.FragmentHomeBinding;
import com.jsa.analytics.model.BalanceSheetModel;
import com.jsa.analytics.model.CashFlowModel;
import com.jsa.analytics.model.FinancialSummaryModel;
import com.jsa.analytics.model.InputModel;
import com.jsa.analytics.ui.CashFlowDashboardActivity;
import com.jsa.analytics.ui.MonthSelectActivity;
import com.opencsv.CSVReader;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HomeFragment extends Fragment implements Toolbar.OnMenuItemClickListener {

    FragmentHomeBinding binding;
    ActivityResultLauncher<Intent> launcher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == RESULT_OK){
                    InputStream inputStream = null;
                    try {
                        inputStream = getContext().getContentResolver().openInputStream(result.getData().getData());
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    if (inputStream==null){
                        Toast.makeText(getContext(), "Unable to Open", Toast.LENGTH_SHORT).show();
                    }else {
                        setCsvData(inputStream);
                    }

                }
            }
    );
    FirebaseAuth firebaseAuth;
    FirebaseFirestore firebaseFirestore;
    Fragment fragment = null;
    FragmentManager fragmentManager;
    FragmentTransaction transaction;
    IFragmentCallback iToggleListener;
    int previousPosition = 0;

    public HomeFragment() {
        // Required empty public constructor
    }

//    public static HomeFragment newInstance(String param1, String param2) {
//        BlankFragment fragment = new BlankFragment();
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
//        return fragment;
//    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            iToggleListener = (IFragmentCallback) context;
        }catch (ClassCastException e){
            throw new ClassCastException(context +"implementation failed");
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater,container,false);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();

        binding.toolbar.setOnMenuItemClickListener(this);
        binding.toolbar.setNavigationOnClickListener(view -> iToggleListener.callBackAction(OPEN_DRAWER));

        fragment = new HomeItemFragment();
        fragmentManager = getFragmentManager();
        transaction = fragmentManager.beginTransaction();
        transaction.replace(binding.container.getId(),fragment);
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        transaction.commit();

        binding.image.setOnClickListener(v -> {
            fragment = new PremiumHomeFragment();
            FragmentManager fm = getFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            ft.replace(binding.container.getId(), fragment);
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            ft.commit();
        });
        binding.tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                switch (tab.getPosition()){
                    case 0:
                        fragment = new HomeItemFragment();
                        if (tab.getPosition() != previousPosition ){
                            ft.setCustomAnimations(android.R.anim.slide_in_left,android.R.anim.slide_out_right);
                        }
                        previousPosition = tab.getPosition();
                        break;
                    case 1:
                        fragment = new AgendaFragment();
                        if (previousPosition == 0){
                            ft.setCustomAnimations(R.anim.slide_in_right,R.anim.slide_out_left);
                        }else {
                            ft.setCustomAnimations(android.R.anim.slide_in_left,android.R.anim.slide_out_right);
                        }
                        previousPosition = tab.getPosition();
                        break;
                    case 2:
                        fragment = new ContactFragment();
                        ft.setCustomAnimations(R.anim.slide_in_right,R.anim.slide_out_left);
                        previousPosition = tab.getPosition();
                        break;
                }

                ft.replace(binding.container.getId(), fragment);
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                ft.commit();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        binding.add.setOnClickListener(view -> {
            AddDialogFragmentLayoutBinding dialogBinding = AddDialogFragmentLayoutBinding.inflate(getLayoutInflater());
            BottomSheetDialog bottomSheet = new BottomSheetDialog(getContext());
            bottomSheet.setContentView(dialogBinding.getRoot());
            bottomSheet.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            bottomSheet.show();

            dialogBinding.addCsv.setOnClickListener(view12 -> {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.addCategory(Intent.CATEGORY_OPENABLE);
                intent.setType("text/csv");
//                        intent.setType("image/*");
                intent.putExtra(Intent.EXTRA_MIME_TYPES, true);
                launcher.launch(intent);
                bottomSheet.dismiss();
            });

            dialogBinding.addNew.setOnClickListener(view1 -> {
                startActivity(new Intent(getContext(), MonthSelectActivity.class));
                bottomSheet.dismiss();
            });

            dialogBinding.close.setOnClickListener(v -> bottomSheet.dismiss());

            dialogBinding.cancel.setOnClickListener(view13 -> bottomSheet.dismiss());
        });

        return binding.getRoot();
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()){
            case R.id.notification:
                iToggleListener.callBackAction(OPEN_NOTIFICATION);
                return true;
        }
        return false;
    }
    private void setCsvData(InputStream inputStream) {
        try {
            CSVReader csvReader = new CSVReader(new InputStreamReader(inputStream));
            String[] cellData = null;
            int i = 0;
            List<InputModel> modelList = new ArrayList<>();
            Map<String,InputModel> modelMap = new HashMap<>();

            while ((cellData = csvReader.readNext())!=null){
                if (i==0||i==1||i==2){

                }else {
                    InputModel inputModel = new InputModel();
                    FinancialSummaryModel financialSummaryModel = new FinancialSummaryModel();
                    BalanceSheetModel balanceSheetModel = new BalanceSheetModel();
                    CashFlowModel cashFlowModel = new CashFlowModel();
                    financialSummaryModel.setRevenue(new FinancialSummaryModel.Revenue(Double.parseDouble(cellData[1]),Double.parseDouble(cellData[2]),"Crore"));
                    financialSummaryModel.setTotalVariableCost(new FinancialSummaryModel.TotalVariableCost(Double.parseDouble(cellData[3]),Double.parseDouble(cellData[4]),"Crore"));
                    financialSummaryModel.setGrossProfit(new FinancialSummaryModel.GrossProfit(Double.parseDouble(cellData[5]),Double.parseDouble(cellData[6]),"Crore"));
                    financialSummaryModel.setGrossProfitRatio(new FinancialSummaryModel.GrossProfitRatio(Double.parseDouble(cellData[7]),Double.parseDouble(cellData[8]),"Crore"));
                    financialSummaryModel.setOtherIncome(new FinancialSummaryModel.OtherIncome(Double.parseDouble(cellData[9]),Double.parseDouble(cellData[10]),"Crore"));
                    financialSummaryModel.setFixedCost(new FinancialSummaryModel.FixedCost(Double.parseDouble(cellData[11]),Double.parseDouble(cellData[12]),"Crore"));
                    financialSummaryModel.setNetProfit(new FinancialSummaryModel.NetProfit(Double.parseDouble(cellData[13]),Double.parseDouble(cellData[14]),"Crore"));
                    financialSummaryModel.setNetProfitRatio(new FinancialSummaryModel.NetProfitRatio(Double.parseDouble(cellData[15]),Double.parseDouble(cellData[16]),"Crore"));

                    balanceSheetModel.setCapital(new BalanceSheetModel.Capital(Double.parseDouble(cellData[17]),Double.parseDouble(cellData[18]),"Crore"));
                    balanceSheetModel.setReservesAndSurplus(new BalanceSheetModel.ReservesAndSurplus(Double.parseDouble(cellData[19]),Double.parseDouble(cellData[20]),"Crore"));
                    balanceSheetModel.setLoan(new BalanceSheetModel.Loan(Double.parseDouble(cellData[21]),Double.parseDouble(cellData[22]),"Crore"));
                    balanceSheetModel.setCreditors(new BalanceSheetModel.Creditors(Double.parseDouble(cellData[23]),Double.parseDouble(cellData[24]),"Crore"));
                    balanceSheetModel.setOtherLiabilities(new BalanceSheetModel.OtherLiabilities(Double.parseDouble(cellData[25]),Double.parseDouble(cellData[26]),"Crore"));
                    balanceSheetModel.setFixedAsset(new BalanceSheetModel.FixedAsset(Double.parseDouble(cellData[27]),Double.parseDouble(cellData[28]),"Crore"));
                    balanceSheetModel.setInvestment(new BalanceSheetModel.Investment(Double.parseDouble(cellData[29]),Double.parseDouble(cellData[30]),"Crore"));
                    balanceSheetModel.setDebtors(new BalanceSheetModel.Debtors(Double.parseDouble(cellData[31]),Double.parseDouble(cellData[32]),"Crore"));
                    balanceSheetModel.setInventory(new BalanceSheetModel.Inventory(Double.parseDouble(cellData[33]),Double.parseDouble(cellData[34]),"Crore"));
                    balanceSheetModel.setCashAndBank(new BalanceSheetModel.CashAndBank(Double.parseDouble(cellData[35]),Double.parseDouble(cellData[36]),"Crore"));
                    balanceSheetModel.setOtherAsset(new BalanceSheetModel.OtherAsset(Double.parseDouble(cellData[37]),Double.parseDouble(cellData[38]),"Crore"));
                    balanceSheetModel.setDepreciation(new BalanceSheetModel.Depreciation(Double.parseDouble(cellData[39]),Double.parseDouble(cellData[40]),"Crore"));

                    cashFlowModel.setCollectionFromCustomer(new CashFlowModel.CollectionFromCustomer(Double.parseDouble(cellData[41]),Double.parseDouble(cellData[42]),"crore"));
                    cashFlowModel.setCapitalIntroduce(new CashFlowModel.CapitalIntroduce(Double.parseDouble(cellData[43]),Double.parseDouble(cellData[44]),"crore"));
                    cashFlowModel.setLoanTaken(new CashFlowModel.LoanTaken(Double.parseDouble(cellData[45]),Double.parseDouble(cellData[46]),"crore"));
                    cashFlowModel.setFixedAssetSold(new CashFlowModel.FixedAssetSold(Double.parseDouble(cellData[47]),Double.parseDouble(cellData[48]),"crore"));
                    cashFlowModel.setInvestmentSold(new CashFlowModel.InvestmentSold(Double.parseDouble(cellData[49]),Double.parseDouble(cellData[50]),"crore"));
                    cashFlowModel.setVariableCost(new CashFlowModel.VariableCost(Double.parseDouble(cellData[51]),Double.parseDouble(cellData[52]),"crore"));
                    cashFlowModel.setCapitalWithdrawal(new CashFlowModel.CapitalWithdrawal(Double.parseDouble(cellData[53]),Double.parseDouble(cellData[54]),"crore"));
                    cashFlowModel.setLoanPayment(new CashFlowModel.LoanPayment(Double.parseDouble(cellData[55]),Double.parseDouble(cellData[56]),"crore"));
                    cashFlowModel.setFixedAssetsBuy(new CashFlowModel.FixedAssetsBuy(Double.parseDouble(cellData[57]),Double.parseDouble(cellData[58]),"crore"));
                    cashFlowModel.setInvestmentBuy(new CashFlowModel.InvestmentBuy(Double.parseDouble(cellData[59]),Double.parseDouble(cellData[60]),"crore"));

                    inputModel.setFinancialSummary(financialSummaryModel);
                    inputModel.setBalanceSheet(balanceSheetModel);
                    inputModel.setCashFlow(cashFlowModel);
                    inputModel.setYearMonthName(cellData[0]);
                    modelList.add(inputModel);
                    modelMap.put(cellData[0],inputModel);
                }
                i++;
            }
            firebaseFirestore.collection("analyticsData").document(firebaseAuth.getUid()).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                    if (task.isSuccessful()){
                        DocumentSnapshot document = task.getResult();
                        if (document.exists()){
                            for (InputModel inputModel:modelList){
                                firebaseFirestore.collection("analyticsData").document(firebaseAuth.getUid()).update(inputModel.getYearMonthName(),inputModel).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()){

                                        }else {
                                            Toast.makeText(getContext(), task.getException().toString(), Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                            }
                        }else {
                            firebaseFirestore.collection("analyticsData").document(firebaseAuth.getUid()).set(modelMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {

                                }
                            });
                        }
                    }else {
                        Toast.makeText(getContext(), task.getException().toString(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }catch (Exception e){
            Toast.makeText(getContext(), e.toString(), Toast.LENGTH_SHORT).show();
        }
    }

}