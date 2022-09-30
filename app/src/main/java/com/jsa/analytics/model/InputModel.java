package com.jsa.analytics.model;

import java.io.Serializable;

public class InputModel implements Serializable {
    private FinancialSummaryModel financialSummary;
    private BalanceSheetModel balanceSheet;
    private CashFlowModel cashFlow;

    public FinancialSummaryModel getFinancialSummary() {
        return financialSummary;
    }

    public void setFinancialSummary(FinancialSummaryModel financialSummary) {
        this.financialSummary = financialSummary;
    }

    public BalanceSheetModel getBalanceSheet() {
        return balanceSheet;
    }

    public void setBalanceSheet(BalanceSheetModel balanceSheet) {
        this.balanceSheet = balanceSheet;
    }

    public CashFlowModel getCashFlow() {
        return cashFlow;
    }

    public void setCashFlow(CashFlowModel cashFlow) {
        this.cashFlow = cashFlow;
    }
}
