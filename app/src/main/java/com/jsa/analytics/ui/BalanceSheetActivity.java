package com.jsa.analytics.ui;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.jsa.analytics.R;
import com.jsa.analytics.databinding.ActivityBalanceSheetBinding;
import com.jsa.analytics.model.BalanceSheetModel;
import com.jsa.analytics.model.FinancialSummaryModel;
import com.jsa.analytics.model.InputModel;
import com.jsa.analytics.utils.StaticFields;

import java.text.SimpleDateFormat;
import java.util.Date;

public class BalanceSheetActivity extends AppCompatActivity {

    private String TAG = getClass().getName();
    ActivityBalanceSheetBinding binding;
    InputModel inputModel = new InputModel();
    BalanceSheetModel balanceSheetModel = new BalanceSheetModel();
    FirebaseAuth firebaseAuth;
    FirebaseFirestore firebaseFirestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityBalanceSheetBinding.inflate(getLayoutInflater());
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

        if (getIntent().getStringExtra("date")!=null){
            binding.gotoThirdInput.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    getInputData();
                    StaticFields.balanceSheetModel = balanceSheetModel;
                    Intent intent = new Intent(getApplicationContext(),CashFlowActivity.class);
                    intent.putExtra("date",getIntent().getStringExtra("date"));
                    startActivity(intent);
                }
            });
        }else {
            setDataOnFields(StaticFields.editModel);
            binding.gotoThirdInput.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    getInputData();
                    StaticFields.editModel.setBalanceSheet(balanceSheetModel);
                    Intent intent = new Intent(getApplicationContext(),CashFlowActivity.class);
                    intent.putExtra("editDate",getIntent().getStringExtra("editDate"));
                    startActivity(intent);
                }
            });
        }

        viewsListener();

    }

    private void setDataOnFields(InputModel editModel) {
        binding.capital.actualInput.setText(String.valueOf(editModel.getBalanceSheet().getCapital().getActualData()));
        binding.capital.planInput.setText(String.valueOf(editModel.getBalanceSheet().getCapital().getExpectedData()));
        binding.reserveSurplus.actualInput.setText(String.valueOf(editModel.getBalanceSheet().getReservesAndSurplus().getActualData()));
        binding.reserveSurplus.planInput.setText(String.valueOf(editModel.getBalanceSheet().getReservesAndSurplus().getExpectedData()));
        binding.loan.actualInput.setText(String.valueOf(editModel.getBalanceSheet().getReservesAndSurplus().getActualData()));
        binding.loan.planInput.setText(String.valueOf(editModel.getBalanceSheet().getReservesAndSurplus().getExpectedData()));
        binding.creditors.actualInput.setText(String.valueOf(editModel.getBalanceSheet().getCreditors().getActualData()));
        binding.creditors.planInput.setText(String.valueOf(editModel.getBalanceSheet().getCreditors().getExpectedData()));
        binding.otherLiabilities.actualInput.setText(String.valueOf(editModel.getBalanceSheet().getOtherLiabilities().getActualData()));
        binding.otherLiabilities.planInput.setText(String.valueOf(editModel.getBalanceSheet().getOtherLiabilities().getExpectedData()));
        binding.fixedAsset.actualInput.setText(String.valueOf(editModel.getBalanceSheet().getFixedAsset().getActualData()));
        binding.fixedAsset.planInput.setText(String.valueOf(editModel.getBalanceSheet().getFixedAsset().getExpectedData()));
        binding.investment.actualInput.setText(String.valueOf(editModel.getBalanceSheet().getInvestment().getActualData()));
        binding.investment.planInput.setText(String.valueOf(editModel.getBalanceSheet().getInvestment().getExpectedData()));
        binding.debtors.actualInput.setText(String.valueOf(editModel.getBalanceSheet().getDebtors().getActualData()));
        binding.debtors.planInput.setText(String.valueOf(editModel.getBalanceSheet().getDebtors().getExpectedData()));
        binding.inventory.actualInput.setText(String.valueOf(editModel.getBalanceSheet().getInventory().getActualData()));
        binding.inventory.planInput.setText(String.valueOf(editModel.getBalanceSheet().getInventory().getExpectedData()));
        binding.cashAndBank.actualInput.setText(String.valueOf(editModel.getBalanceSheet().getCashAndBank().getActualData()));
        binding.cashAndBank.planInput.setText(String.valueOf(editModel.getBalanceSheet().getCashAndBank().getExpectedData()));
        binding.otherAssets.actualInput.setText(String.valueOf(editModel.getBalanceSheet().getCashAndBank().getActualData()));
        binding.otherAssets.planInput.setText(String.valueOf(editModel.getBalanceSheet().getCashAndBank().getExpectedData()));
        binding.depreciation.actualInput.setText(String.valueOf(editModel.getBalanceSheet().getDepreciation().getActualData()==null?0:editModel.getBalanceSheet().getDepreciation().getActualData()));
        binding.depreciation.planInput.setText(String.valueOf(editModel.getBalanceSheet().getDepreciation().getExpectedData()==null?0:editModel.getBalanceSheet().getDepreciation().getExpectedData()));
    }

    private void viewsListener() {
        binding.capital.actualInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                actualTotal();
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
        binding.capital.planInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                actualTotal();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        binding.reserveSurplus.actualInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                actualTotal();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        binding.reserveSurplus.planInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                actualTotal();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        binding.loan.actualInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                actualTotal();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        binding.loan.planInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                actualTotal();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        binding.creditors.actualInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                actualTotal();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        binding.creditors.planInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                actualTotal();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        binding.otherLiabilities.actualInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                actualTotal();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        binding.otherLiabilities.planInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                actualTotal();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        binding.fixedAsset.actualInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                planTotal();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        binding.fixedAsset.planInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                planTotal();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        binding.investment.actualInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                planTotal();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        binding.investment.planInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                planTotal();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        binding.debtors.actualInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                planTotal();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        binding.debtors.planInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                planTotal();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        binding.inventory.actualInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                planTotal();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        binding.inventory.planInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                planTotal();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        binding.cashAndBank.actualInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                planTotal();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        binding.cashAndBank.planInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                planTotal();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        binding.otherAssets.actualInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                planTotal();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        binding.otherAssets.planInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                planTotal();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    private void getInputData() {
        balanceSheetModel.setCapital(new BalanceSheetModel.Capital(
                Double.parseDouble(binding.capital.actualInput.getText().toString().isEmpty()?"0":binding.capital.actualInput.getText().toString()),
                Double.parseDouble(binding.capital.planInput.getText().toString().isEmpty()?"0":binding.capital.planInput.getText().toString()),
                "crore"));
        balanceSheetModel.setReservesAndSurplus(new BalanceSheetModel.ReservesAndSurplus(
                Double.parseDouble(binding.reserveSurplus.actualInput.getText().toString().isEmpty()?"0":binding.reserveSurplus.actualInput.getText().toString()),
                Double.parseDouble(binding.reserveSurplus.planInput.getText().toString().isEmpty()?"0":binding.reserveSurplus.planInput.getText().toString()),
                "crore"));
        balanceSheetModel.setLoan(new BalanceSheetModel.Loan(
                Double.parseDouble(binding.loan.actualInput.getText().toString().isEmpty()?"0":binding.loan.actualInput.getText().toString()),
                Double.parseDouble(binding.loan.planInput.getText().toString().isEmpty()?"0":binding.loan.planInput.getText().toString()),
                "crore"));
        balanceSheetModel.setCreditors(new BalanceSheetModel.Creditors(
                Double.parseDouble(binding.creditors.actualInput.getText().toString().isEmpty()?"0":binding.creditors.actualInput.getText().toString()),
                Double.parseDouble(binding.creditors.planInput.getText().toString().isEmpty()?"0":binding.creditors.planInput.getText().toString()),
                "crore"));
        balanceSheetModel.setOtherLiabilities(new BalanceSheetModel.OtherLiabilities(
                Double.parseDouble(binding.otherLiabilities.actualInput.getText().toString().isEmpty()?"0":binding.otherLiabilities.actualInput.getText().toString()),
                Double.parseDouble(binding.otherLiabilities.planInput.getText().toString().isEmpty()?"0":binding.otherLiabilities.planInput.getText().toString()),
                "crore"));
        balanceSheetModel.setFixedAsset(new BalanceSheetModel.FixedAsset(
                Double.parseDouble(binding.fixedAsset.actualInput.getText().toString().isEmpty()?"0":binding.fixedAsset.actualInput.getText().toString()),
                Double.parseDouble(binding.fixedAsset.planInput.getText().toString().isEmpty()?"0":binding.fixedAsset.planInput.getText().toString()),
                "crore"));
        balanceSheetModel.setInvestment(new BalanceSheetModel.Investment(
                Double.parseDouble(binding.investment.actualInput.getText().toString().isEmpty()?"0":binding.investment.actualInput.getText().toString()),
                Double.parseDouble(binding.investment.planInput.getText().toString().isEmpty()?"0":binding.investment.planInput.getText().toString()),
                "crore"));
        balanceSheetModel.setDebtors(new BalanceSheetModel.Debtors(
                Double.parseDouble(binding.debtors.actualInput.getText().toString().isEmpty()?"0":binding.debtors.actualInput.getText().toString()),
                Double.parseDouble(binding.debtors.planInput.getText().toString().isEmpty()?"0":binding.debtors.planInput.getText().toString()),
                "crore"));
        balanceSheetModel.setInventory(new BalanceSheetModel.Inventory(
                Double.parseDouble(binding.inventory.actualInput.getText().toString().isEmpty()?"0":binding.inventory.actualInput.getText().toString()),
                Double.parseDouble(binding.inventory.planInput.getText().toString().isEmpty()?"0":binding.inventory.planInput.getText().toString()),
                "crore"));
        balanceSheetModel.setCashAndBank(new BalanceSheetModel.CashAndBank(
                Double.parseDouble(binding.cashAndBank.actualInput.getText().toString().isEmpty()?"0":binding.cashAndBank.actualInput.getText().toString()),
                Double.parseDouble(binding.cashAndBank.planInput.getText().toString().isEmpty()?"0":binding.cashAndBank.planInput.getText().toString()),
                "crore"));
        balanceSheetModel.setOtherAsset(new BalanceSheetModel.OtherAsset(
                Double.parseDouble(binding.otherAssets.actualInput.getText().toString().isEmpty()?"0":binding.otherAssets.actualInput.getText().toString()),
                Double.parseDouble(binding.otherAssets.planInput.getText().toString().isEmpty()?"0":binding.otherAssets.planInput.getText().toString()),
                "crore"));
        balanceSheetModel.setDepreciation(new BalanceSheetModel.Depreciation(
                Double.parseDouble(binding.depreciation.actualInput.getText().toString().isEmpty()?"0":binding.depreciation.actualInput.getText().toString()),
                Double.parseDouble(binding.depreciation.planInput.getText().toString().isEmpty()?"0":binding.depreciation.planInput.getText().toString()),
                "crore"));
        inputModel.setBalanceSheet(balanceSheetModel);
    }

    private void planTotal() {
        Double planFixedAssets = Double.parseDouble(binding.fixedAsset.planInput.getText().toString().isEmpty() ? "0" : binding.fixedAsset.planInput.getText().toString());
        Double actualFixedAssets = Double.parseDouble(binding.fixedAsset.actualInput.getText().toString().isEmpty() ? "0" : binding.fixedAsset.actualInput.getText().toString());
        Double planInvestment = Double.parseDouble(binding.investment.planInput.getText().toString().isEmpty() ? "0" : binding.investment.planInput.getText().toString());
        Double actualInvestment = Double.parseDouble(binding.investment.actualInput.getText().toString().isEmpty() ? "0" : binding.investment.actualInput.getText().toString());
        Double planDebtors = Double.parseDouble(binding.debtors.planInput.getText().toString().isEmpty() ? "0" : binding.debtors.planInput.getText().toString());
        Double actualDebtors = Double.parseDouble(binding.debtors.actualInput.getText().toString().isEmpty() ? "0" : binding.debtors.actualInput.getText().toString());
        Double planInventory = Double.parseDouble(binding.inventory.planInput.getText().toString().isEmpty() ? "0" : binding.inventory.planInput.getText().toString());
        Double actualInventory = Double.parseDouble(binding.inventory.actualInput.getText().toString().isEmpty() ? "0" : binding.inventory.actualInput.getText().toString());
        Double planCash = Double.parseDouble(binding.cashAndBank.planInput.getText().toString().isEmpty() ? "0" : binding.cashAndBank.planInput.getText().toString());
        Double actualCash = Double.parseDouble(binding.cashAndBank.actualInput.getText().toString().isEmpty() ? "0" : binding.cashAndBank.actualInput.getText().toString());
        Double planOtherAssets = Double.parseDouble(binding.otherAssets.planInput.getText().toString().isEmpty() ? "0" : binding.otherAssets.planInput.getText().toString());
        Double actualOtherAssets = Double.parseDouble(binding.otherAssets.actualInput.getText().toString().isEmpty() ? "0" : binding.otherAssets.actualInput.getText().toString());
        String totalActual = String.valueOf(actualCash + actualDebtors + actualInventory + actualInvestment + actualOtherAssets + actualFixedAssets);
        String totalPlanned = String.valueOf(planCash + planDebtors + planInventory + planInvestment + planFixedAssets + planOtherAssets);
        binding.actualTotal1.setText(totalActual);
        binding.plannedTotal1.setText(totalPlanned);
    }

    private void actualTotal() {
        Double actualCapital = Double.parseDouble(binding.capital.actualInput.getText().toString().isEmpty() ? "0" : binding.capital.actualInput.getText().toString());
        Double plannedCapital = Double.parseDouble(binding.capital.planInput.getText().toString().isEmpty() ? "0" : binding.capital.planInput.getText().toString());
        Double actualReserveSurplus = Double.parseDouble(binding.reserveSurplus.actualInput.getText().toString().isEmpty() ? "0" : binding.reserveSurplus.actualInput.getText().toString());
        Double plannedReserveSurplus = Double.parseDouble(binding.reserveSurplus.planInput.getText().toString().isEmpty() ? "0" : binding.reserveSurplus.planInput.getText().toString());
        Double plannedLoan = Double.parseDouble(binding.loan.planInput.getText().toString().isEmpty() ? "0" : binding.loan.planInput.getText().toString());
        Double actualLoan = Double.parseDouble(binding.loan.actualInput.getText().toString().isEmpty() ? "0" : binding.loan.actualInput.getText().toString());
        Double plannedCreditors = Double.parseDouble(binding.creditors.planInput.getText().toString().isEmpty() ? "0" : binding.creditors.planInput.getText().toString());
        Double actualCreditors = Double.parseDouble(binding.creditors.actualInput.getText().toString().isEmpty() ? "0" : binding.creditors.actualInput.getText().toString());
        Double plannedOtherLiabilities = Double.parseDouble(binding.otherLiabilities.planInput.getText().toString().isEmpty() ? "0" : binding.otherLiabilities.planInput.getText().toString());
        Double actualOtherLiabilities = Double.parseDouble(binding.otherLiabilities.actualInput.getText().toString().isEmpty() ? "0" : binding.otherLiabilities.actualInput.getText().toString());
        String totalActual = String.valueOf(actualCapital + actualReserveSurplus + actualLoan + actualCreditors + actualOtherLiabilities);
        String totalPlanned = String.valueOf(plannedCapital + plannedReserveSurplus + plannedLoan + plannedCreditors + plannedOtherLiabilities);
        binding.actualTotal.setText(totalActual);
        binding.plannedTotal.setText(totalPlanned);
    }

}