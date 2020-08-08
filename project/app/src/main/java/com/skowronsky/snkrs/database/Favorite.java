package com.skowronsky.snkrs.database;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity
public class Favorite {
    @PrimaryKey(autoGenerate = true)
    public int id_favorite_shoes;
    public int id_shoes;
    public int id_base;
    public double size;

    public Favorite(){};

    @Ignore
    public Favorite(Shoes shoes, double size, int id_base){
        this.id_shoes = shoes.id_shoes;
        this.size = size;
        this.id_base = id_base;
    }
}