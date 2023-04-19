package com.example.rocketapp.model;

public class DetailRocketInfo {

    //Fields declaration
    private String rocketName;
    private boolean status;
    private long costPerLaunch;
    private double successPct;
    private double height_meter;
    private double height_feet;
    private double diameter_meter;
    private double diameter_feet;

    private String description;

    private String[] imgUrl;

    public DetailRocketInfo(String rocketName, boolean status, long costPerLaunch,
                            double successPct, double height_meter,
                            double height_feet, double diameter_meter,
                            double diameter_feet, String description, String[] imgUrl) {
        this.rocketName = rocketName;
        this.status = status;
        this.costPerLaunch = costPerLaunch;
        this.successPct = successPct;
        this.height_meter = height_meter;
        this.height_feet = height_feet;
        this.diameter_meter = diameter_meter;
        this.diameter_feet = diameter_feet;
        this.description = description;
        this.imgUrl = imgUrl;
    }


    public String getRocketName() {
        return rocketName;
    }

    public boolean isStatus() {
        return status;
    }

    public long getCostPerLaunch() {
        return costPerLaunch;
    }

    public double getSuccessPct() {
        return successPct;
    }

    public double getHeight_meter() {
        return height_meter;
    }

    public double getHeight_feet() {
        return height_feet;
    }

    public double getDiameter_meter() {
        return diameter_meter;
    }

    public double getDiameter_feet() {
        return diameter_feet;
    }

    public String getDescription() {
        return description;
    }

    public String[] getImgUrl() {
        return imgUrl;
    }
}
