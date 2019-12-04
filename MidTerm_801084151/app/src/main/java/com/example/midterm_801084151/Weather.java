package com.example.midterm_801084151;

import java.io.Serializable;


public class Weather implements Serializable {
    String minTemp;
    String maxTemp;
    String windSpeed;
    String humidity;
    String temp;
    String date ,desc, imageIcon;

    public Weather() {
        this.minTemp = minTemp;
        this.maxTemp = maxTemp;
        this.windSpeed = windSpeed;
        this.humidity = humidity;
        this.temp = temp;
        this.date = date;
        this.desc = desc;
        this.imageIcon = imageIcon;
    }

    public String getMinTemp() {
        return minTemp;
    }

    public void setMinTemp(String minTemp) {
        this.minTemp = minTemp;
    }

    public String getMaxTemp() {
        return maxTemp;
    }

    public void setMaxTemp(String maxTemp) {
        this.maxTemp = maxTemp;
    }

    public String getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(String windSpeed) {
        this.windSpeed = windSpeed;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getImageIcon() {
        return imageIcon;
    }

    public void setImageIcon(String imageIcon) {
        this.imageIcon = imageIcon;
    }


    @Override
    public String toString() {
        return "Weather{" +
                "minTemp='" + minTemp + '\'' +
                ", maxTemp='" + maxTemp + '\'' +
                ", windSpeed='" + windSpeed + '\'' +
                ", humidity='" + humidity + '\'' +
                ", temp='" + temp + '\'' +
                ", date='" + date + '\'' +
                ", desc='" + desc + '\'' +
                ", imageIcon='" + imageIcon + '\'' +
                '}';
    }
}



