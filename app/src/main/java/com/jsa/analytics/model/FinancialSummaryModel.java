package com.jsa.analytics.model;

import android.os.Parcelable;

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

    public static class Revenue{
        private Plan plan;
        private Actual actual;

        public Revenue(Plan plan, Actual actual) {
            this.plan = plan;
            this.actual = actual;
        }

        public Plan getPlan() {
            return plan;
        }

        public void setPlan(Plan plan) {
            this.plan = plan;
        }

        public Actual getActual() {
            return actual;
        }

        public void setActual(Actual actual) {
            this.actual = actual;
        }
    }
    public static class TotalVariableCost{
        private Plan plan;
        private Actual actual;

        public TotalVariableCost(Plan plan, Actual actual) {
            this.plan = plan;
            this.actual = actual;
        }

        public Plan getPlan() {
            return plan;
        }

        public void setPlan(Plan plan) {
            this.plan = plan;
        }

        public Actual getActual() {
            return actual;
        }

        public void setActual(Actual actual) {
            this.actual = actual;
        }
    }
    public static class GrossProfit{
        private Plan plan;
        private Actual actual;

        public GrossProfit(Plan plan, Actual actual) {
            this.plan = plan;
            this.actual = actual;
        }

        public Plan getPlan() {
            return plan;
        }

        public void setPlan(Plan plan) {
            this.plan = plan;
        }

        public Actual getActual() {
            return actual;
        }

        public void setActual(Actual actual) {
            this.actual = actual;
        }
    }
    public static class GrossProfitRatio{
        private Plan plan;
        private Actual actual;

        public GrossProfitRatio(Plan plan, Actual actual) {
            this.plan = plan;
            this.actual = actual;
        }

        public Plan getPlan() {
            return plan;
        }

        public void setPlan(Plan plan) {
            this.plan = plan;
        }

        public Actual getActual() {
            return actual;
        }

        public void setActual(Actual actual) {
            this.actual = actual;
        }
    }
    public static class OtherIncome{
        private Plan plan;
        private Actual actual;

        public OtherIncome(Plan plan, Actual actual) {
            this.plan = plan;
            this.actual = actual;
        }

        public Plan getPlan() {
            return plan;
        }

        public void setPlan(Plan plan) {
            this.plan = plan;
        }

        public Actual getActual() {
            return actual;
        }

        public void setActual(Actual actual) {
            this.actual = actual;
        }
    }
    public static class FixedCost{
        private Plan plan;
        private Actual actual;

        public FixedCost(Plan plan, Actual actual) {
            this.plan = plan;
            this.actual = actual;
        }

        public Plan getPlan() {
            return plan;
        }

        public void setPlan(Plan plan) {
            this.plan = plan;
        }

        public Actual getActual() {
            return actual;
        }

        public void setActual(Actual actual) {
            this.actual = actual;
        }
    }
    public static class NetProfit{
        private Plan plan;
        private Actual actual;

        public NetProfit(Plan plan, Actual actual) {
            this.plan = plan;
            this.actual = actual;
        }

        public Plan getPlan() {
            return plan;
        }

        public void setPlan(Plan plan) {
            this.plan = plan;
        }

        public Actual getActual() {
            return actual;
        }

        public void setActual(Actual actual) {
            this.actual = actual;
        }
    }
    public static class NetProfitRatio{
        private Plan plan;
        private Actual actual;

        public NetProfitRatio(Plan plan, Actual actual) {
            this.plan = plan;
            this.actual = actual;
        }

        public Plan getPlan() {
            return plan;
        }

        public void setPlan(Plan plan) {
            this.plan = plan;
        }

        public Actual getActual() {
            return actual;
        }

        public void setActual(Actual actual) {
            this.actual = actual;
        }
    }

    public static class Plan{
        String unit;
        float data;

        public Plan(float data) {
            this.data = data;
        }

        public String getUnit() {
            return unit;
        }

        public void setUnit(String unit) {
            this.unit = unit;
        }

        public float getData() {
            return data;
        }

        public void setData(Float data) {
            this.data = data;
        }
    }
    public static class Actual{
        String unit;
        float data;

        public Actual(float data) {
            this.data = data;
        }

        public String getUnit() {
            return unit;
        }

        public void setUnit(String unit) {
            this.unit = unit;
        }

        public float getData() {
            return data;
        }

        public void setData(int data) {
            this.data = data;
        }
    }
}
