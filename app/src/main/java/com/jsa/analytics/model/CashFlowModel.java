package com.jsa.analytics.model;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

public class CashFlowModel implements Serializable {

    private CollectionFromCustomer collectionFromCustomer;
    private CapitalIntroduce capitalIntroduce;
    private LoanTaken loanTaken;
    private FixedAssetSold fixedAssetSold;
    private InvestmentSold investmentSold;
    private VariableCost variableCost;
    private CapitalWithdrawal capitalWithdrawal;
    private LoanPayment loanPayment;
    private FixedAssetsBuy fixedAssetsBuy;
    private InvestmentBuy investmentBuy;

    public CollectionFromCustomer getCollectionFromCustomer() {
        return collectionFromCustomer;
    }

    public void setCollectionFromCustomer(CollectionFromCustomer collectionFromCustomer) {
        this.collectionFromCustomer = collectionFromCustomer;
    }

    public CapitalIntroduce getCapitalIntroduce() {
        return capitalIntroduce;
    }

    public void setCapitalIntroduce(CapitalIntroduce capitalIntroduce) {
        this.capitalIntroduce = capitalIntroduce;
    }

    public LoanTaken getLoanTaken() {
        return loanTaken;
    }

    public void setLoanTaken(LoanTaken loanTaken) {
        this.loanTaken = loanTaken;
    }

    public FixedAssetSold getFixedAssetSold() {
        return fixedAssetSold;
    }

    public void setFixedAssetSold(FixedAssetSold fixedAssetSold) {
        this.fixedAssetSold = fixedAssetSold;
    }

    public InvestmentSold getInvestmentSold() {
        return investmentSold;
    }

    public void setInvestmentSold(InvestmentSold investmentSold) {
        this.investmentSold = investmentSold;
    }

    public VariableCost getVariableCost() {
        return variableCost;
    }

    public void setVariableCost(VariableCost variableCost) {
        this.variableCost = variableCost;
    }

    public CapitalWithdrawal getCapitalWithdrawal() {
        return capitalWithdrawal;
    }

    public void setCapitalWithdrawal(CapitalWithdrawal capitalWithdrawal) {
        this.capitalWithdrawal = capitalWithdrawal;
    }

    public LoanPayment getLoanPayment() {
        return loanPayment;
    }

    public void setLoanPayment(LoanPayment loanPayment) {
        this.loanPayment = loanPayment;
    }

    public FixedAssetsBuy getFixedAssetsBuy() {
        return fixedAssetsBuy;
    }

    public void setFixedAssetsBuy(FixedAssetsBuy fixedAssetsBuy) {
        this.fixedAssetsBuy = fixedAssetsBuy;
    }

    public InvestmentBuy getInvestmentBuy() {
        return investmentBuy;
    }

    public void setInvestmentBuy(InvestmentBuy investmentBuy) {
        this.investmentBuy = investmentBuy;
    }

    public static class CollectionFromCustomer implements Serializable{
        Double actualData;
        Double expectedData;
        String unit;

        public CollectionFromCustomer(Double actualData, Double expectedData, String unit) {
            this.actualData = actualData;
            this.expectedData = expectedData;
            this.unit = unit;
        }

        public Double getActualData() {
            return actualData;
        }

        public void setActualData(Double actualData) {
            this.actualData = actualData;
        }

        public Double getExpectedData() {
            return expectedData;
        }

        public void setExpectedData(Double expectedData) {
            this.expectedData = expectedData;
        }

        public String getUnit() {
            return unit;
        }

        public void setUnit(String unit) {
            this.unit = unit;
        }
    }
    public static class CapitalIntroduce implements Serializable{
        Double actualData;
        Double expectedData;
        String unit;

        public CapitalIntroduce(Double actualData, Double expectedData, String unit) {
            this.actualData = actualData;
            this.expectedData = expectedData;
            this.unit = unit;
        }

        public Double getActualData() {
            return actualData;
        }

        public void setActualData(Double actualData) {
            this.actualData = actualData;
        }

        public Double getExpectedData() {
            return expectedData;
        }

        public void setExpectedData(Double expectedData) {
            this.expectedData = expectedData;
        }

        public String getUnit() {
            return unit;
        }

        public void setUnit(String unit) {
            this.unit = unit;
        }
    }
    public static class LoanTaken implements Serializable{
        Double actualData;
        Double expectedData;
        String unit;

        public LoanTaken(Double actualData, Double expectedData, String unit) {
            this.actualData = actualData;
            this.expectedData = expectedData;
            this.unit = unit;
        }

        public Double getActualData() {
            return actualData;
        }

        public void setActualData(Double actualData) {
            this.actualData = actualData;
        }

        public Double getExpectedData() {
            return expectedData;
        }

        public void setExpectedData(Double expectedData) {
            this.expectedData = expectedData;
        }

        public String getUnit() {
            return unit;
        }

        public void setUnit(String unit) {
            this.unit = unit;
        }
    }
    public static class FixedAssetSold implements Serializable{
        Double actualData;
        Double expectedData;
        String unit;

        public FixedAssetSold(Double actualData, Double expectedData, String unit) {
            this.actualData = actualData;
            this.expectedData = expectedData;
            this.unit = unit;
        }

        public Double getActualData() {
            return actualData;
        }

        public void setActualData(Double actualData) {
            this.actualData = actualData;
        }

        public Double getExpectedData() {
            return expectedData;
        }

        public void setExpectedData(Double expectedData) {
            this.expectedData = expectedData;
        }

        public String getUnit() {
            return unit;
        }

        public void setUnit(String unit) {
            this.unit = unit;
        }
    }
    public static class InvestmentSold implements Serializable{
        Double actualData;
        Double expectedData;
        String unit;

        public InvestmentSold(Double actualData, Double expectedData, String unit) {
            this.actualData = actualData;
            this.expectedData = expectedData;
            this.unit = unit;
        }

        public Double getActualData() {
            return actualData;
        }

        public void setActualData(Double actualData) {
            this.actualData = actualData;
        }

        public Double getExpectedData() {
            return expectedData;
        }

        public void setExpectedData(Double expectedData) {
            this.expectedData = expectedData;
        }

        public String getUnit() {
            return unit;
        }

        public void setUnit(String unit) {
            this.unit = unit;
        }
    }
    public static class VariableCost implements Serializable{
        Double actualData;
        Double expectedData;
        String unit;

        public VariableCost(Double actualData, Double expectedData, String unit) {
            this.actualData = actualData;
            this.expectedData = expectedData;
            this.unit = unit;
        }

        public Double getActualData() {
            return actualData;
        }

        public void setActualData(Double actualData) {
            this.actualData = actualData;
        }

        public Double getExpectedData() {
            return expectedData;
        }

        public void setExpectedData(Double expectedData) {
            this.expectedData = expectedData;
        }

        public String getUnit() {
            return unit;
        }

        public void setUnit(String unit) {
            this.unit = unit;
        }
    }
    public static class CapitalWithdrawal implements Serializable{
        Double actualData;
        Double expectedData;
        String unit;

        public CapitalWithdrawal(Double actualData, Double expectedData, String unit) {
            this.actualData = actualData;
            this.expectedData = expectedData;
            this.unit = unit;
        }

        public Double getActualData() {
            return actualData;
        }

        public void setActualData(Double actualData) {
            this.actualData = actualData;
        }

        public Double getExpectedData() {
            return expectedData;
        }

        public void setExpectedData(Double expectedData) {
            this.expectedData = expectedData;
        }

        public String getUnit() {
            return unit;
        }

        public void setUnit(String unit) {
            this.unit = unit;
        }
    }
    public static class LoanPayment implements Serializable{
        Double actualData;
        Double expectedData;
        String unit;

        public LoanPayment(Double actualData, Double expectedData, String unit) {
            this.actualData = actualData;
            this.expectedData = expectedData;
            this.unit = unit;
        }

        public Double getActualData() {
            return actualData;
        }

        public void setActualData(Double actualData) {
            this.actualData = actualData;
        }

        public Double getExpectedData() {
            return expectedData;
        }

        public void setExpectedData(Double expectedData) {
            this.expectedData = expectedData;
        }

        public String getUnit() {
            return unit;
        }

        public void setUnit(String unit) {
            this.unit = unit;
        }
    }
    public static class FixedAssetsBuy implements Serializable{
        Double actualData;
        Double expectedData;
        String unit;

        public FixedAssetsBuy(Double actualData, Double expectedData, String unit) {
            this.actualData = actualData;
            this.expectedData = expectedData;
            this.unit = unit;
        }

        public Double getActualData() {
            return actualData;
        }

        public void setActualData(Double actualData) {
            this.actualData = actualData;
        }

        public Double getExpectedData() {
            return expectedData;
        }

        public void setExpectedData(Double expectedData) {
            this.expectedData = expectedData;
        }

        public String getUnit() {
            return unit;
        }

        public void setUnit(String unit) {
            this.unit = unit;
        }
    }
    public static class InvestmentBuy implements Serializable{
        Double actualData;
        Double expectedData;
        String unit;

        public InvestmentBuy(Double actualData, Double expectedData, String unit) {
            this.actualData = actualData;
            this.expectedData = expectedData;
            this.unit = unit;
        }

        public InvestmentBuy() {
        }

        public InvestmentBuy parse(JSONObject obj){
            InvestmentBuy investmentBuy = new InvestmentBuy();
            try {
                investmentBuy.actualData = obj.getDouble("actualData");
                investmentBuy.expectedData = obj.getDouble("expectedData");
                investmentBuy.unit = obj.getString("unit");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return investmentBuy;
        }

        public Double getActualData() {
            return actualData;
        }

        public void setActualData(Double actualData) {
            this.actualData = actualData;
        }

        public Double getExpectedData() {
            return expectedData;
        }

        public void setExpectedData(Double expectedData) {
            this.expectedData = expectedData;
        }

        public String getUnit() {
            return unit;
        }

        public void setUnit(String unit) {
            this.unit = unit;
        }
    }
}
