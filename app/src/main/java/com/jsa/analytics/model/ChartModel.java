package com.jsa.analytics.model;

public class ChartModel {
    String title;
    Double actualData;
    Double planData;

    public ChartModel(String title, Double actualData, Double planData) {
        this.title = title;
        this.actualData = actualData;
        this.planData = planData;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Double getActualData() {
        return actualData;
    }

    public void setActualData(Double actualData) {
        this.actualData = actualData;
    }

    public Double getPlanData() {
        return planData;
    }

    public void setPlanData(Double planData) {
        this.planData = planData;
    }
}
