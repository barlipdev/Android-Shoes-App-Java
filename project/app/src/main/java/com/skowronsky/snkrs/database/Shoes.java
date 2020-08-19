package com.skowronsky.snkrs.database;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity()
public class Shoes {
    @PrimaryKey
    @SerializedName("idShoes")
    @Expose
    public long idShoes;

    @SerializedName("brandName")
    @Expose
    public String brandName;

    @SerializedName("modelName")
    @Expose
    public String modelName;

    @SerializedName("factor")
    @Expose
    public double factor;

    @SerializedName("image")
    @Expose
    public String image;

    @SerializedName("brand")
    @Expose
    @Ignore
    public Brand brand;


    public long getIdShoes() {
        return idShoes;
    }

    public void setIdShoes(long idShoes) {
        this.idShoes = idShoes;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
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

    public Shoes() {
    }

    @Ignore
    public Shoes(int idShoes, String brandName, String modelName, double factor, String image) {
        this.idShoes = idShoes;
        this.brandName = brandName;
        this.modelName = modelName;
        this.factor = factor;
        this.image = image;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }
}
