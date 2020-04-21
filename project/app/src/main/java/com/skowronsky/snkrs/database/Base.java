package com.skowronsky.snkrs.database;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Base {
    @PrimaryKey()
    public int id_base;
    public int id_shoes;
    public double size;
    //factor of base + selected size
    public double hiddenSize;

    public Base(){};
    public Base(int id_base, Shoes shoes, int size){
        this.id_base = id_base;
        this.id_shoes = shoes.id_shoes;
        this.hiddenSize = shoes.factor + this.size;
        this.size = size;
    }
}
