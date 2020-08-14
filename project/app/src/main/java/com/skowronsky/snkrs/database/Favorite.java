package com.skowronsky.snkrs.database;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity
public class Favorite {
    @PrimaryKey(autoGenerate = true)
    public int idFavoriteShoes;
    public int idShoes;
    public int idBase;
    public double size;

    public Favorite(){};

    @Ignore
    public Favorite(Shoes shoes, double size, int idBase){
        this.idShoes = shoes.idShoes;
        this.size = size;
        this.idBase = idBase;
    }
}