package com.jsa.analytics.model;

public class BalanceSheetModel {

    private Capital capital;
    private ReservesAndSurplus reservesAndSurplus;
    private Loan loan;
    private Creditors creditors;
    private OtherLiabilities otherLiabilities;
    private FixedAsset fixedAsset;
    private Investment investment;
    private Debtors debtors;
    private Inventory inventory;
    private CashAndBank cashAndBank;
    private OtherAsset otherAsset;

    public Capital getCapital() {
        return capital;
    }

    public void setCapital(Capital capital) {
        this.capital = capital;
    }

    public ReservesAndSurplus getReservesAndSurplus() {
        return reservesAndSurplus;
    }

    public void setReservesAndSurplus(ReservesAndSurplus reservesAndSurplus) {
        this.reservesAndSurplus = reservesAndSurplus;
    }

    public Loan getLoan() {
        return loan;
    }

    public void setLoan(Loan loan) {
        this.loan = loan;
    }

    public Creditors getCreditors() {
        return creditors;
    }

    public void setCreditors(Creditors creditors) {
        this.creditors = creditors;
    }

    public OtherLiabilities getOtherLiabilities() {
        return otherLiabilities;
    }

    public void setOtherLiabilities(OtherLiabilities otherLiabilities) {
        this.otherLiabilities = otherLiabilities;
    }

    public FixedAsset getFixedAsset() {
        return fixedAsset;
    }

    public void setFixedAsset(FixedAsset fixedAsset) {
        this.fixedAsset = fixedAsset;
    }

    public Investment getInvestment() {
        return investment;
    }

    public void setInvestment(Investment investment) {
        this.investment = investment;
    }

    public Debtors getDebtors() {
        return debtors;
    }

    public void setDebtors(Debtors debtors) {
        this.debtors = debtors;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    public CashAndBank getCashAndBank() {
        return cashAndBank;
    }

    public void setCashAndBank(CashAndBank cashAndBank) {
        this.cashAndBank = cashAndBank;
    }

    public OtherAsset getOtherAsset() {
        return otherAsset;
    }

    public void setOtherAsset(OtherAsset otherAsset) {
        this.otherAsset = otherAsset;
    }

    public static class Capital{
        private Plan plan;
        private Actual actual;

        public Capital(Plan plan, Actual actual) {
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
    public static class ReservesAndSurplus{
        private Plan plan;
        private Actual actual;

        public ReservesAndSurplus(Plan plan, Actual actual) {
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
    public static class Loan{
        private Plan plan;
        private Actual actual;

        public Loan(Plan plan, Actual actual) {
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
    public static class Creditors{
        private Plan plan;
        private Actual actual;

        public Creditors(Plan plan, Actual actual) {
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
    public static class OtherLiabilities{
        private Plan plan;
        private Actual actual;

        public OtherLiabilities(Plan plan, Actual actual) {
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
    public static class FixedAsset{
        private Plan plan;
        private Actual actual;

        public FixedAsset(Plan plan, Actual actual) {
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
    public static class Investment{
        private Plan plan;
        private Actual actual;

        public Investment(Plan plan, Actual actual) {
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
    public static class Debtors{
        private Plan plan;
        private Actual actual;

        public Debtors(Plan plan, Actual actual) {
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
    public static class Inventory{
        private Plan plan;
        private Actual actual;

        public Inventory(Plan plan, Actual actual) {
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
    public static class CashAndBank{
        private Plan plan;
        private Actual actual;

        public CashAndBank(Plan plan, Actual actual) {
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
    public static class OtherAsset{
        private Plan plan;
        private Actual actual;

        public OtherAsset(Plan plan, Actual actual) {
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
