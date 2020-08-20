package com.skowronsky.snkrs.database;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity
public class Brand {
    @PrimaryKey
    @SerializedName("idBrand")
    @Expose
    private long idBrand;

    @SerializedName("name")
    @Expose
    private String brandName;

    @SerializedName("image")
    @Expose
    private String image;

    public long getIdBrand() {
        return idBrand;
    }

    public void setIdBrand(long idBrand) {
        this.idBrand = idBrand;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Brand(){}

    @Ignore
    public Brand(int idBrand, String brandName, String image){
        this.idBrand = idBrand;
        this.brandName = brandName;
        this.image = image;
    }
}
