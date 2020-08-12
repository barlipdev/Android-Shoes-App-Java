package com.skowronsky.snkrs.database;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity(tableName = "size_chart")
public class SizeChart {

    @PrimaryKey
    @SerializedName("idSize")
    @Expose
    private Long idSize;
    @SerializedName("us")
    @Expose
    private double us;
    @SerializedName("uk")
    @Expose
    private double uk;
    @SerializedName("eu")
    @Expose
    private String eu;

    @SerializedName("type")
    @Expose
    private char type;

    private String brandName;

    @SerializedName("brands")
    @Expose
    @Ignore
    private Brand brand;

    public SizeChart(){}

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public Long getIdSize() {
        return idSize;
    }

    public void setIdSize(Long idSize) {
        this.idSize = idSize;
    }

    public double getUs() {
        return us;
    }

    public void setUs(double us) {
        this.us = us;
    }

    public double getUk() {
        return uk;
    }

    public void setUk(double uk) {
        this.uk = uk;
    }

    public String getEu() {
        return eu;
    }

    public void setEu(String eu) {
        this.eu = eu;
    }

    public char getType() {
        return type;
    }

    public void setType(char type) {
        this.type = type;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }



}
