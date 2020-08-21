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
    private long idShoes;

    private long idBrand;

    @SerializedName("modelName")
    @Expose
    private String modelName;

    @SerializedName("factor")
    @Expose
    private double factor;

    @SerializedName("image")
    @Expose
    private String image;

    @SerializedName("brand")
    @Expose
    @Ignore
    private Brand brand;

    @Ignore
    public Shoes(long idShoes, long idBrand, String modelName, double factor, String image) {
        this.idShoes = idShoes;
        this.idBrand = idBrand;
        this.modelName = modelName;
        this.factor = factor;
        this.image = image;
    }

    public void assignValues(){
        idBrand = brand.getIdBrand();
    }

    public long getIdShoes() {
        return idShoes;
    }

    public void setIdShoes(long idShoes) {
        this.idShoes = idShoes;
    }

    public long getIdBrand() {
        return idBrand;
    }

    public void setIdBrand(long idBrand) {
        this.idBrand = idBrand;
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

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }
}
