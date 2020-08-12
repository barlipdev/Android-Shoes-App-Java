package com.skowronsky.snkrs.database;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity
public class Base {
    @PrimaryKey(autoGenerate = true)
    public int idBase;
    public int idShoes;
    public double size;
    //factor of base + selected size
    public double hiddenSize;
    

    public Base(){};

    @Ignore
    public Base(Shoes shoes, double size){
        this.idShoes = shoes.idShoes;
        this.hiddenSize = shoes.factor + this.size;
        this.size = size;
    }
}
