package com.skowronsky.snkrs.database;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Favorite {
    @PrimaryKey(autoGenerate = true)
    public int id_favorite_shoes;
    public int id_shoes;
    public double size;

    public Favorite(){};
    public Favorite(Shoes shoes, double size){
        this.id_shoes = shoes.id_shoes;
        this.size = size;
    }
}