package com.skowronsky.snkrs.database;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Base {
    @PrimaryKey(autoGenerate = true)
    public int id_base;
    public int id_shoes;
    public double size;
    //factor of base + selected size
    public double hiddenSize;

    public Base(){};
    public Base(Shoes shoes, double size){
        this.id_shoes = shoes.id_shoes;
        this.hiddenSize = shoes.factor + this.size;
        this.size = size;
    }
}
