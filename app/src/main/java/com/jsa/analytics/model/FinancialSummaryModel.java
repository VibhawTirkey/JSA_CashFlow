package com.jsa.analytics.model;

import java.io.Serializable;

public class FinancialSummaryModel implements Serializable {

    private Revenue revenue;
    private TotalVariableCost totalVariableCost;
    private GrossProfit grossProfit;
    private GrossProfitRatio grossProfitRatio;
    private OtherIncome otherIncome;
    private FixedCost fixedCost;
    private NetProfit netProfit;
    private NetProfitRatio netProfitRatio;
    
    public Revenue getRevenue() {
        return revenue;
    }

    public void setRevenue(Revenue revenue) {
        this.revenue = revenue;
    }

    public TotalVariableCost getTotalVariableCost() {
        return totalVariableCost;
    }

    public void setTotalVariableCost(TotalVariableCost totalVariableCost) {
        this.totalVariableCost = totalVariableCost;
    }

    public GrossProfit getGrossProfit() {
        return grossProfit;
    }

    public void setGrossProfit(GrossProfit grossProfit) {
        this.grossProfit = grossProfit;
    }

    public GrossProfitRatio getGrossProfitRatio() {
        return grossProfitRatio;
    }

    public void setGrossProfitRatio(GrossProfitRatio grossProfitRatio) {
        this.grossProfitRatio = grossProfitRatio;
    }

    public OtherIncome getOtherIncome() {
        return otherIncome;
    }

    public void setOtherIncome(OtherIncome otherIncome) {
        this.otherIncome = otherIncome;
    }

    public FixedCost getFixedCost() {
        return fixedCost;
    }

    public void setFixedCost(FixedCost fixedCost) {
        this.fixedCost = fixedCost;
    }

    public NetProfit getNetProfit() {
        return netProfit;
    }

    public void setNetProfit(NetProfit netProfit) {
        this.netProfit = netProfit;
    }

    public NetProfitRatio getNetProfitRatio() {
        return netProfitRatio;
    }

    public void setNetProfitRatio(NetProfitRatio netProfitRatio) {
        this.netProfitRatio = netProfitRatio;
    }

    public static class Revenue implements Serializable{
        Double actualData;
        Double expectedData;
        String unit;

        public Revenue(Double actualData, Double expectedData, String unit) {
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
    public static class TotalVariableCost implements Serializable{
        Double actualData;
        Double expectedData;
        String unit;

        public TotalVariableCost(Double actualData, Double expectedData, String unit) {
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
    public static class GrossProfit implements Serializable{
        Double actualData;
        Double expectedData;
        String unit;

        public GrossProfit(Double actualData, Double expectedData, String unit) {
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
    public static class GrossProfitRatio implements Serializable{
        Double actualData;
        Double expectedData;
        String unit;

        public GrossProfitRatio(Double actualData, Double expectedData, String unit) {
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
    public static class OtherIncome implements Serializable{
        Double actualData;
        Double expectedData;
        String unit;

        public OtherIncome(Double actualData, Double expectedData, String unit) {
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
    public static class FixedCost implements Serializable{
        Double actualData;
        Double expectedData;
        String unit;

        public FixedCost(Double actualData, Double expectedData, String unit) {
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
    public static class NetProfit implements Serializable{
        Double actualData;
        Double expectedData;
        String unit;

        public NetProfit(Double actualData, Double expectedData, String unit) {
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
    public static class NetProfitRatio implements Serializable{
        Double actualData;
        Double expectedData;
        String unit;

        public NetProfitRatio(Double actualData, Double expectedData, String unit) {
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
}
