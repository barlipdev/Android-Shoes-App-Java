package com.skowronsky.snkrs.database;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity()
public class Shoes {
    @PrimaryKey
    @SerializedName("idShoes")
    @Expose
    public int id_shoes;

    @SerializedName("idBrand")
    @Expose
    public String brand_name;

    @SerializedName("modelName")
    @Expose
    public String modelName;

    @SerializedName("factor")
    @Expose
    public double factor;

    public int getId_shoes() {
        return id_shoes;
    }

    public void setId_shoes(int id_shoes) {
        this.id_shoes = id_shoes;
    }

    public String getBrand_name() {
        return brand_name;
    }

    public void setBrand_name(String brand_name) {
        this.brand_name = brand_name;
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

    @SerializedName("image")
    @Expose
    public String image;

    public Shoes() {
    }

    public Shoes(int id_shoes, String brand_name, String modelName, double factor, String image) {
        this.id_shoes = id_shoes;
        this.brand_name = brand_name;
        this.modelName = modelName;
        this.factor = factor;
        this.image = image;
    }
}
