package com.jsa.analytics.model;

public class BannersModel {

    private String userId;
    private String createdDate;
    private String imgUrl;
    private Boolean isVisible;

    public BannersModel(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}
