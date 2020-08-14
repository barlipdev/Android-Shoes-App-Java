package com.skowronsky.snkrs.database;


import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class BrandShoes implements Serializable {
    @PrimaryKey
    @SerializedName("idShoes")
    @Expose
    public int idShoes;

    @SerializedName("brand")
    @Expose
    public Brand brand;

    @SerializedName("modelName")
    @Expose
    public String modelName;

    @SerializedName("factor")
    @Expose
    public double factor;

    @SerializedName("image")
    @Expose
    public String image;




    public int getIdShoes() {
        return idShoes;
    }

    public void setIdShoes(int idShoes) {
        this.idShoes = idShoes;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public double getFactor() {
        return factor;
    }

    public void setFactor(double factor) {
        this.factor = factor;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }


}
