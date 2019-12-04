package com.example.midterm_801084151;

public class weatherForcast {
    String minTempf;
    String maxTempf;
    String windSpeedf;
    String humidityf;
    String tempf;
    String datef ,descf, imageIconf;

    public weatherForcast() {
        this.minTempf = minTempf;
        this.maxTempf = maxTempf;
        this.windSpeedf = windSpeedf;
        this.humidityf = humidityf;
        this.tempf = tempf;
        this.datef = datef;
        this.descf = descf;
        this.imageIconf = imageIconf;
    }

    public String getMinTempf() {
        return minTempf;
    }

    public void setMinTempf(String minTempf) {
        this.minTempf = minTempf;
    }

    public String getMaxTempf() {
        return maxTempf;
    }

    public void setMaxTempf(String maxTempf) {
        this.maxTempf = maxTempf;
    }

    public String getWindSpeedf() {
        return windSpeedf;
    }

    public void setWindSpeedf(String windSpeedf) {
        this.windSpeedf = windSpeedf;
    }

    public String getHumidityf() {
        return humidityf;
    }

    public void setHumidityf(String humidityf) {
        this.humidityf = humidityf;
    }

    public String getTempf() {
        return tempf;
    }

    public void setTempf(String tempf) {
        this.tempf = tempf;
    }

    public String getDatef() {
        return datef;
    }

    public void setDatef(String datef) {
        this.datef = datef;
    }

    public String getDescf() {
        return descf;
    }

    public void setDescf(String descf) {
        this.descf = descf;
    }

    public String getImageIconf() {
        return imageIconf;
    }

    public void setImageIconf(String imageIconf) {
        this.imageIconf = imageIconf;
    }


    @Override
    public String toString() {
        return "weatherForcast{" +
                "minTempf='" + minTempf + '\'' +
                ", maxTempf='" + maxTempf + '\'' +
                ", windSpeedf='" + windSpeedf + '\'' +
                ", humidityf='" + humidityf + '\'' +
                ", tempf='" + tempf + '\'' +
                ", datef='" + datef + '\'' +
                ", descf='" + descf + '\'' +
                ", imageIconf='" + imageIconf + '\'' +
                '}';
    }
}


