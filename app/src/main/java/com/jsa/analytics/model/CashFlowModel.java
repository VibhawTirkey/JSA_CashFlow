package com.jsa.analytics.model;

public class CashFlowModel {

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

    public static class CollectionFromCustomer{
        private Plan plan;
        private Actual actual;

        public CollectionFromCustomer(Plan plan, Actual actual) {
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
    public static class CapitalIntroduce{
        private Plan plan;
        private Actual actual;

        public CapitalIntroduce(Plan plan, Actual actual) {
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
    public static class LoanTaken{
        private Plan plan;
        private Actual actual;

        public LoanTaken(Plan plan, Actual actual) {
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
    public static class FixedAssetSold{
        private Plan plan;
        private Actual actual;

        public FixedAssetSold(Plan plan, Actual actual) {
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
    public static class InvestmentSold{
        private Plan plan;
        private Actual actual;

        public InvestmentSold(Plan plan, Actual actual) {
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
    public static class VariableCost{
        private Plan plan;
        private Actual actual;

        public VariableCost(Plan plan, Actual actual) {
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
    public static class CapitalWithdrawal{
        private Plan plan;
        private Actual actual;

        public CapitalWithdrawal(Plan plan, Actual actual) {
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
    public static class LoanPayment{
        private Plan plan;
        private Actual actual;

        public LoanPayment(Plan plan, Actual actual) {
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
    public static class FixedAssetsBuy{
        private Plan plan;
        private Actual actual;

        public FixedAssetsBuy(Plan plan, Actual actual) {
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
    public static class InvestmentBuy{
        private Plan plan;
        private Actual actual;

        public InvestmentBuy(Plan plan, Actual actual) {
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
