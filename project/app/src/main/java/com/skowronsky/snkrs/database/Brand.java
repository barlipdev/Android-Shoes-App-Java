package com.skowronsky.snkrs.database;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity
public class Brand {
    @PrimaryKey
    @SerializedName("idBrand")
    @Expose
    public int id_brand;

    @SerializedName("name")
    @Expose
    public String brand_name;

    @SerializedName("image")
    @Expose
    public String image;

    public int getId_brand() {
        return id_brand;
    }

    public void setId_brand(int id_brand) {
        this.id_brand = id_brand;
    }

    public String getBrand_name() {
        return brand_name;
    }

    public void setBrand_name(String brand_name) {
        this.brand_name = brand_name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Brand(){}

    public Brand(int id_brand, String brand_name, String image){
        this.id_brand = id_brand;
        this.brand_name = brand_name;
        this.image = image;
    }
}
