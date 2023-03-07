package com.jsa.analytics.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.transition.AutoTransition;
import androidx.transition.TransitionManager;

import com.jsa.analytics.databinding.ActivityAnalyticsOutputBinding;
import com.jsa.analytics.databinding.ItemCashflowDashboardMonthBinding;
import com.jsa.analytics.databinding.MonthOutputItemBinding;
import com.jsa.analytics.model.InputModel;
import com.jsa.analytics.ui.AnalyticsOutputActivity;
import com.jsa.analytics.ui.FinancialSummaryActivity;
import com.jsa.analytics.utils.StaticFields;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class TableViewAdapter extends RecyclerView.Adapter<TableViewAdapter.TableViewHolder> {

    private Context context;
    private List<Integer> date;
    private List<InputModel> inputModelList;

    public TableViewAdapter(Context context, List<Integer> date, List<InputModel> inputModelList) {
        this.context = context;
        this.date = date;
        this.inputModelList = inputModelList;
    }

    @NonNull
    @Override
    public TableViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemCashflowDashboardMonthBinding binding = ItemCashflowDashboardMonthBinding.inflate(LayoutInflater.from(context),parent,false);
        return new TableViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull TableViewHolder holder, int position) {
        int dateMonth = date.get(position);
        InputModel inputModel = inputModelList.get(position);
        try {
            Date date1 = new SimpleDateFormat("yyyyMM").parse(String.valueOf(dateMonth));
            holder.binding.month.setText(new SimpleDateFormat("MMMM yyyy").format(date1));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        holder.binding.headContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (holder.binding.tableContainer.getVisibility() == View.VISIBLE){
                    TransitionManager.beginDelayedTransition(holder.binding.getRoot(), new AutoTransition());
                    holder.binding.tableContainer.setVisibility(View.GONE);
//                    holder.binding.indicator.setImageResource(R.drawable.round_arrow_forward);
                }else {
                    TransitionManager.beginDelayedTransition(holder.binding.getRoot(), new AutoTransition());
                    holder.binding.tableContainer.setVisibility(View.VISIBLE);
//                    holder.binding.indicator.setImageResource(R.drawable.round_add);
                }
            }
        });

        Double actualGpp = inputModel.getFinancialSummary().getGrossProfitRatio().getActualData()==null?0:inputModel.getFinancialSummary().getGrossProfitRatio().getActualData();
        Double planGpp = inputModel.getFinancialSummary().getGrossProfitRatio().getExpectedData()==null?0:inputModel.getFinancialSummary().getGrossProfitRatio().getExpectedData();
        Double actualNpp = inputModel.getFinancialSummary().getNetProfitRatio().getActualData()==null?0:inputModel.getFinancialSummary().getNetProfitRatio().getActualData();
        Double planNpp = inputModel.getFinancialSummary().getNetProfitRatio().getExpectedData()==null?0:inputModel.getFinancialSummary().getNetProfitRatio().getExpectedData();
        Double actualRoi =inputModel.getFinancialSummary().getNetProfit().getActualData()==null?0:inputModel.getFinancialSummary().getNetProfit().getActualData()/
                (inputModel.getBalanceSheet().getCapital().getActualData()==null?0:inputModel.getBalanceSheet().getCapital().getActualData());
        Double planRoi =inputModel.getFinancialSummary().getNetProfit().getExpectedData()==null?0:inputModel.getFinancialSummary().getNetProfit().getExpectedData()/
                (inputModel.getBalanceSheet().getCapital().getExpectedData()==null?0:inputModel.getBalanceSheet().getCapital().getExpectedData());
        Double actualInventoryTurn = inputModel.getFinancialSummary().getTotalVariableCost().getActualData()==null?0:inputModel.getFinancialSummary().getTotalVariableCost().getActualData()/
                (inputModel.getBalanceSheet().getInventory().getActualData()==null?0:inputModel.getBalanceSheet().getInventory().getActualData());
        Double planInventoryTurn = inputModel.getFinancialSummary().getTotalVariableCost().getExpectedData()==null?0:inputModel.getFinancialSummary().getTotalVariableCost().getExpectedData()/
                (inputModel.getBalanceSheet().getInventory().getExpectedData()==null?0:inputModel.getBalanceSheet().getInventory().getExpectedData());
        Double actualCurrentAsset = inputModel.getBalanceSheet().getDebtors().getActualData()==null?0:inputModel.getBalanceSheet().getDebtors().getActualData()+
                (inputModel.getBalanceSheet().getInventory().getActualData()==null?0:inputModel.getBalanceSheet().getInventory().getActualData())+
                (inputModel.getBalanceSheet().getOtherAsset().getActualData()==null?0:inputModel.getBalanceSheet().getOtherAsset().getActualData());
        Double planCurrentAsset = inputModel.getBalanceSheet().getDebtors().getExpectedData()==null?0:inputModel.getBalanceSheet().getDebtors().getExpectedData()+
                (inputModel.getBalanceSheet().getInventory().getExpectedData()==null?0:inputModel.getBalanceSheet().getInventory().getExpectedData())+
                (inputModel.getBalanceSheet().getOtherAsset().getExpectedData()==null?0:inputModel.getBalanceSheet().getOtherAsset().getExpectedData());
        Double actualCurrentLiability = inputModel.getBalanceSheet().getCreditors().getActualData()==null?0:inputModel.getBalanceSheet().getCreditors().getActualData()+
                (inputModel.getBalanceSheet().getOtherLiabilities().getActualData()==null?0:inputModel.getBalanceSheet().getOtherLiabilities().getActualData());
        Double planCurrentLiability = inputModel.getBalanceSheet().getCreditors().getExpectedData()==null?0:inputModel.getBalanceSheet().getCreditors().getExpectedData()+
                (inputModel.getBalanceSheet().getOtherLiabilities().getExpectedData()==null?0:inputModel.getBalanceSheet().getOtherLiabilities().getExpectedData());
        Double actualQR = (actualCurrentAsset-(inputModel.getBalanceSheet().getInventory().getActualData()==null?0:inputModel.getBalanceSheet().getInventory().getActualData()))/
                actualCurrentLiability;
        Double planQR = (planCurrentAsset-(inputModel.getBalanceSheet().getInventory().getExpectedData()==null?0:inputModel.getBalanceSheet().getInventory().getExpectedData()))/
                planCurrentLiability;
        Double actualDebtorDays = 365/(inputModel.getFinancialSummary().getRevenue().getActualData()==null?0:inputModel.getFinancialSummary().getRevenue().getActualData())*
                (inputModel.getBalanceSheet().getDebtors().getActualData()==null?0:inputModel.getBalanceSheet().getDebtors().getActualData());
        Double planDebtorDays = 365/(inputModel.getFinancialSummary().getRevenue().getExpectedData()==null?0:inputModel.getFinancialSummary().getRevenue().getExpectedData())*
                (inputModel.getBalanceSheet().getDebtors().getExpectedData()==null?0:inputModel.getBalanceSheet().getDebtors().getExpectedData());
        Double actualInventoryDays = 365/(inputModel.getFinancialSummary().getTotalVariableCost().getActualData()==null?0:inputModel.getFinancialSummary().getTotalVariableCost().getActualData())*
                (inputModel.getBalanceSheet().getInventory().getActualData()==null?0:inputModel.getBalanceSheet().getInventory().getActualData());
        Double planInventoryDays = 365/(inputModel.getFinancialSummary().getTotalVariableCost().getExpectedData()==null?0:inputModel.getFinancialSummary().getTotalVariableCost().getExpectedData())*
                (inputModel.getBalanceSheet().getInventory().getExpectedData()==null?0:inputModel.getBalanceSheet().getInventory().getExpectedData());
        Double actualCreditorDays = 365/((inputModel.getFinancialSummary().getTotalVariableCost().getActualData()==null?0:inputModel.getFinancialSummary().getTotalVariableCost().getActualData())+
                (inputModel.getBalanceSheet().getInventory().getActualData()==null?0:inputModel.getBalanceSheet().getInventory().getActualData())-
                (inputModel.getBalanceSheet().getInventory().getActualData()==null?0:inputModel.getBalanceSheet().getInventory().getActualData()))*
                (inputModel.getBalanceSheet().getCreditors().getActualData()==null?0:inputModel.getBalanceSheet().getCreditors().getActualData());
        Double planCreditorDays = 365/((inputModel.getFinancialSummary().getTotalVariableCost().getExpectedData()==null?0:inputModel.getFinancialSummary().getTotalVariableCost().getExpectedData())+
                (inputModel.getBalanceSheet().getInventory().getExpectedData()==null?0:inputModel.getBalanceSheet().getInventory().getExpectedData())-
                (inputModel.getBalanceSheet().getInventory().getExpectedData()==null?0:inputModel.getBalanceSheet().getInventory().getExpectedData()))*
                (inputModel.getBalanceSheet().getCreditors().getExpectedData()==null?0:inputModel.getBalanceSheet().getCreditors().getExpectedData());
        Double actualCccDays = actualDebtorDays+actualInventoryDays+actualCreditorDays;
        Double planCccDays = planDebtorDays+planInventoryDays+planCreditorDays;
        Double actualWorkingCapital = actualCurrentAsset-actualCurrentLiability;
        Double planWorkingCapital = planCurrentAsset-planCurrentLiability;
        Double actualDER = (inputModel.getBalanceSheet().getLoan().getActualData()==null?0:inputModel.getBalanceSheet().getLoan().getActualData())/
                ((inputModel.getBalanceSheet().getCapital().getActualData()==null?0:inputModel.getBalanceSheet().getCapital().getActualData())+
                        (inputModel.getBalanceSheet().getReservesAndSurplus().getActualData()==null?0:inputModel.getBalanceSheet().getReservesAndSurplus().getActualData()));
        Double planDER = (inputModel.getBalanceSheet().getLoan().getExpectedData()==null?0:inputModel.getBalanceSheet().getLoan().getExpectedData())/
                ((inputModel.getBalanceSheet().getCapital().getExpectedData()==null?0:inputModel.getBalanceSheet().getCapital().getExpectedData())+
                        (inputModel.getBalanceSheet().getReservesAndSurplus().getExpectedData()==null?0:inputModel.getBalanceSheet().getReservesAndSurplus().getExpectedData()));
        Double actualSale = inputModel.getFinancialSummary().getRevenue().getActualData()==null?0:inputModel.getFinancialSummary().getRevenue().getActualData();
        Double planSale = inputModel.getFinancialSummary().getRevenue().getExpectedData()==null?0:inputModel.getFinancialSummary().getRevenue().getExpectedData();
        Double actualGp = inputModel.getFinancialSummary().getGrossProfit().getActualData()==null?0:inputModel.getFinancialSummary().getGrossProfit().getActualData();
        Double planGp = inputModel.getFinancialSummary().getGrossProfit().getExpectedData()==null?0:inputModel.getFinancialSummary().getGrossProfit().getExpectedData();
        Double actualNp = inputModel.getFinancialSummary().getNetProfit().getActualData()==null?0:inputModel.getFinancialSummary().getNetProfit().getActualData();
        Double planNp = inputModel.getFinancialSummary().getNetProfit().getExpectedData()==null?0:inputModel.getFinancialSummary().getNetProfit().getExpectedData();
        Double actualCashProfit = inputModel.getFinancialSummary().getNetProfit().getActualData()==null?0:inputModel.getFinancialSummary().getNetProfit().getActualData()+
                (inputModel.getBalanceSheet().getDepreciation().getActualData()==null?0:inputModel.getBalanceSheet().getDepreciation().getActualData())-
                (actualCurrentAsset-actualCurrentLiability-actualDebtorDays-actualInventoryDays-
                        (inputModel.getBalanceSheet().getOtherAsset().getActualData()==null?0:inputModel.getBalanceSheet().getOtherAsset().getActualData())+
                        actualCreditorDays+
                        (inputModel.getBalanceSheet().getOtherLiabilities().getActualData()==null?0:inputModel.getBalanceSheet().getOtherLiabilities().getActualData()));
        Double planCashProfit = inputModel.getFinancialSummary().getNetProfit().getExpectedData()==null?0:inputModel.getFinancialSummary().getNetProfit().getExpectedData()+
                (inputModel.getBalanceSheet().getDepreciation().getExpectedData()==null?0:inputModel.getBalanceSheet().getDepreciation().getExpectedData())-
                (planCurrentAsset-planCurrentLiability-planCreditorDays-planInventoryDays-
                        (inputModel.getBalanceSheet().getOtherAsset().getExpectedData()==null?0:inputModel.getBalanceSheet().getOtherAsset().getExpectedData())+
                        planCreditorDays+
                        (inputModel.getBalanceSheet().getOtherLiabilities().getExpectedData()==null?0:inputModel.getBalanceSheet().getOtherLiabilities().getExpectedData()));

        Double actualCashAndBank = inputModel.getBalanceSheet().getCashAndBank().getActualData()==null?0:inputModel.getBalanceSheet().getCashAndBank().getActualData();
        Double planCashAndBank = inputModel.getBalanceSheet().getCashAndBank().getExpectedData()==null?0:inputModel.getBalanceSheet().getCashAndBank().getExpectedData();
        Double actualNetWorth = inputModel.getBalanceSheet().getCapital().getActualData()==null?0:inputModel.getBalanceSheet().getCapital().getActualData()+
                (inputModel.getBalanceSheet().getReservesAndSurplus().getActualData()==null?0:inputModel.getBalanceSheet().getReservesAndSurplus().getActualData());
        Double planNetWorth = inputModel.getBalanceSheet().getCapital().getExpectedData()==null?0:inputModel.getBalanceSheet().getCapital().getExpectedData()+
                (inputModel.getBalanceSheet().getReservesAndSurplus().getExpectedData()==null?0:inputModel.getBalanceSheet().getReservesAndSurplus().getExpectedData());

        holder.binding.actualGpp.setText(String.format("%.2f",actualGpp));
        holder.binding.planGpp.setText(String.format("%.2f",planGpp));
        holder.binding.actualNpp.setText(String.format("%.2f",actualNpp));
        holder.binding.planNpp.setText(String.format("%.2f",planNpp));
        holder.binding.actualRoi.setText(String.format("%.2f",actualRoi));
        holder.binding.planRoi.setText(String.format("%.2f",planRoi));
        holder.binding.actualInventoryTurn.setText(String.format("%.2f",actualInventoryTurn));
        holder.binding.planInventoryTurn.setText(String.format("%.2f",planInventoryTurn));
        holder.binding.actualCurrentRatio.setText(String.format("%.2f",actualCurrentAsset/actualCurrentLiability));
        holder.binding.planCurrentRatio.setText(String.format("%.2f",planCurrentAsset/planCurrentLiability));
        holder.binding.actualQuickRatio.setText(String.format("%.2f",actualQR));
        holder.binding.planQuickRatio.setText(String.format("%.2f",planQR));
        holder.binding.actualDebtorDays.setText(String.format("%.2f",actualDebtorDays));
        holder.binding.planDebtorDays.setText(String.format("%.2f",planDebtorDays));
        holder.binding.actualInventoryDays.setText(String.format("%.2f",actualInventoryDays));
        holder.binding.planInventoryDays.setText(String.format("%.2f",planInventoryDays));
        holder.binding.actualCreditorDays.setText(String.format("%.2f",actualCreditorDays));
        holder.binding.planCreditorDays.setText(String.format("%.2f",planCreditorDays));
        holder.binding.actualCccDays.setText(String.format("%.2f",actualCccDays));
        holder.binding.planCccDays.setText(String.format("%.2f",planCccDays));
        holder.binding.actualWorkingCapital.setText(String.format("%.2f",actualWorkingCapital));
        holder.binding.planWorkingCapital.setText(String.format("%.2f",planWorkingCapital));
        holder.binding.actualDer.setText(String.format("%.2f",actualDER));
        holder.binding.planDer.setText(String.format("%.2f",planDER));
        holder.binding.actualSale.setText(String.format("%.2f",actualSale));
        holder.binding.planSale.setText(String.format("%.2f",planSale));
        holder.binding.actualGp.setText(String.format("%.2f",actualGp));
        holder.binding.planGp.setText(String.format("%.2f",planGp));
        holder.binding.actualNp.setText(String.format("%.2f",actualNp));
        holder.binding.planNp.setText(String.format("%.2f",planNp));
        holder.binding.actualCashProfit.setText(String.format("%.2f",actualCashProfit));
        holder.binding.planCashProfit.setText(String.format("%.2f",planCashProfit));
        holder.binding.actualBankBalance.setText(String.format("%.2f",actualCashAndBank));
        holder.binding.planBankBalance.setText(String.format("%.2f",planCashAndBank));
        holder.binding.actualNetWorth.setText(String.format("%.2f",actualNetWorth));
        holder.binding.planNetWorth.setText(String.format("%.2f",planNetWorth));

        holder.binding.gotoEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, FinancialSummaryActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("editDate",String.valueOf(dateMonth) );
                StaticFields.editModel = inputModel;
                context.startActivity(intent);
            }
        });

        holder.binding.gotoChart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, AnalyticsOutputActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                StaticFields.inputChart = inputModel;
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return date.size();
    }

    public class TableViewHolder extends RecyclerView.ViewHolder {
        private ItemCashflowDashboardMonthBinding binding;
        public TableViewHolder(ItemCashflowDashboardMonthBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
