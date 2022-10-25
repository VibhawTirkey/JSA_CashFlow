package com.jsa.analytics.model;

import java.io.Serializable;

public class BalanceSheetModel implements Serializable {

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
    private Depreciation depreciation;

    public Depreciation getDepreciation() {
        return depreciation;
    }

    public void setDepreciation(Depreciation depreciation) {
        this.depreciation = depreciation;
    }

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

    public static class Capital implements Serializable{
        Double actualData;
        Double expectedData;
        String unit;

        public Capital(Double actualData, Double expectedData, String unit) {
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
    public static class ReservesAndSurplus implements Serializable{
        Double actualData;
        Double expectedData;
        String unit;

        public ReservesAndSurplus(Double actualData, Double expectedData, String unit) {
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
    public static class Loan implements Serializable{
        Double actualData;
        Double expectedData;
        String unit;

        public Loan(Double actualData, Double expectedData, String unit) {
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
    public static class Creditors implements Serializable{
        Double actualData;
        Double expectedData;
        String unit;

        public Creditors(Double actualData, Double expectedData, String unit) {
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
    public static class OtherLiabilities implements Serializable{
        Double actualData;
        Double expectedData;
        String unit;

        public OtherLiabilities(Double actualData, Double expectedData, String unit) {
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
    public static class FixedAsset implements Serializable{
        Double actualData;
        Double expectedData;
        String unit;

        public FixedAsset(Double actualData, Double expectedData, String unit) {
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
    public static class Investment implements Serializable{
        Double actualData;
        Double expectedData;
        String unit;

        public Investment(Double actualData, Double expectedData, String unit) {
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
    public static class Debtors implements Serializable{
        Double actualData;
        Double expectedData;
        String unit;

        public Debtors(Double actualData, Double expectedData, String unit) {
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
    public static class Inventory implements Serializable{
        Double actualData;
        Double expectedData;
        String unit;

        public Inventory(Double actualData, Double expectedData, String unit) {
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
    public static class CashAndBank implements Serializable{
        Double actualData;
        Double expectedData;
        String unit;

        public CashAndBank(Double actualData, Double expectedData, String unit) {
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
    public static class OtherAsset implements Serializable{
        Double actualData;
        Double expectedData;
        String unit;

        public OtherAsset(Double actualData, Double expectedData, String unit) {
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
    public static class Depreciation implements Serializable{
        Double actualData;
        Double expectedData;
        String unit;

        public Depreciation(Double actualData, Double expectedData, String unit) {
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
