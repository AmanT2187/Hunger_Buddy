package com.amati.hungerbuddy;

public class donateInfo {
        String personName, personNumber, personLocation, foodQuant, imgUrl;

        public donateInfo() {
        }

    public donateInfo(String personName, String personNumber, String personLocation, String foodQuant, String imgUrl) {
        this.personName = personName;
        this.personNumber = personNumber;
        this.personLocation = personLocation;
        this.foodQuant = foodQuant;
        this.imgUrl = imgUrl;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getFoodQuant() {
        return foodQuant;
    }

    public void setFoodQuant(String foodQuant) {
        this.foodQuant = foodQuant;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public String getPersonNumber() {
        return personNumber;
    }

    public void setPersonNumber(String personNumber) {
        this.personNumber = personNumber;
    }

    public String getPersonLocation() {
        return personLocation;
    }

    public void setPersonLocation(String personLocation) {
        this.personLocation = personLocation;
    }
}
