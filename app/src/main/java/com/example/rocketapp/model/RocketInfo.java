package com.example.rocketapp.model;

public class RocketInfo {

    String countryName;
    String rocketName;
    String imgUrl;
    int engine;

    String rocketId;

    public RocketInfo(String countryName, String rocketName, String imgUrl, int engine, String rocketId) {
        this.countryName = countryName;
        this.rocketName = rocketName;
        this.imgUrl = imgUrl;
        this.engine = engine;
        this.rocketId = rocketId;
    }

    public String getCountryName() {
        return countryName;
    }

    public String getRocketName() {
        return rocketName;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public int getEngine() {
        return engine;
    }

    public String getRocketId() {
        return rocketId;
    }
}
