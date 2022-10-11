package com.amati.hungerbuddy;

public class orphanageInfo {
    String opName, opLocation, opUrl, opGps;

    public orphanageInfo(String opName, String opLocation, String opUrl,String opGps) {
        this.opName = opName;
        this.opLocation = opLocation;
        this.opUrl = opUrl;
        this.opGps = opGps;
    }

    public String getOpGps() {
        return opGps;
    }

    public void setOpGps(String opGps) {
        this.opGps = opGps;
    }

    public orphanageInfo() {

    }

    public String getOpName() {
        return opName;
    }

    public void setOpName(String opName) {
        this.opName = opName;
    }

    public String getOpLocation() {
        return opLocation;
    }

    public void setOpLocation(String opLocation) {
        this.opLocation = opLocation;
    }

    public String getOpUrl() {
        return opUrl;
    }

    public void setOpUrl(String opUrl) {
        this.opUrl = opUrl;
    }
}
