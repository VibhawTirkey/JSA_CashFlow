package com.jsa.analytics.utils;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.jsa.analytics.model.BalanceSheetModel;
import com.jsa.analytics.model.CashFlowModel;
import com.jsa.analytics.model.FinancialSummaryModel;
import com.jsa.analytics.model.UserDetailModel;

public class AppSharedPreferenceManager {
    public static String mySf = "mySf";
    public static String USER_DATA = "userdata";
    public static String FINANCIAL_DATA = "financialData";
    public static String BALANCE_SHEET = "balanceSheet";
    public static String CASH_FLOW = "cashFlow";

    public static void setUserData(Context context, UserDetailModel result) {
        SharedPreferences sp = context.getSharedPreferences(mySf, MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(USER_DATA, new Gson().toJson(result));
        editor.apply();
    }
    public static UserDetailModel getUserData(Context context) {
        SharedPreferences sp = context.getSharedPreferences(mySf, MODE_PRIVATE);
        return new Gson().fromJson(sp.getString(USER_DATA, null), UserDetailModel.class);
    }


    public static void clearUserData(Context context)
    {
        SharedPreferences sharedPreferences = context.getSharedPreferences(mySf,MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }

    public static void setFinancialData(Context context, FinancialSummaryModel model){
        SharedPreferences sharedPreferences = context.getSharedPreferences(mySf,MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(FINANCIAL_DATA,new Gson().toJson(model));
        editor.apply();
    }
    public static void setBalanceSheet(Context context, BalanceSheetModel model){
        SharedPreferences sharedPreferences = context.getSharedPreferences(mySf,MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(BALANCE_SHEET,new Gson().toJson(model));
        editor.apply();
    }
    public static void setCashFlow(Context context, CashFlowModel model){
        SharedPreferences sharedPreferences = context.getSharedPreferences(mySf,MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(CASH_FLOW,new Gson().toJson(model));
        editor.apply();
    }

    public static FinancialSummaryModel getFinancialData(Context context) {
        SharedPreferences sp = context.getSharedPreferences(mySf, MODE_PRIVATE);
        return new Gson().fromJson(sp.getString(FINANCIAL_DATA, null), FinancialSummaryModel.class);
    }
    public static UserDetailModel getBalanceData(Context context) {
        SharedPreferences sp = context.getSharedPreferences(mySf, MODE_PRIVATE);
        return new Gson().fromJson(sp.getString(BALANCE_SHEET, null), UserDetailModel.class);
    }
    public static UserDetailModel getCashflowData(Context context) {
        SharedPreferences sp = context.getSharedPreferences(mySf, MODE_PRIVATE);
        return new Gson().fromJson(sp.getString(CASH_FLOW, null), UserDetailModel.class);
    }
    public static void clearFinancialData(Context context)
    {
        SharedPreferences sharedPreferences = context.getSharedPreferences(mySf,MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }
}
