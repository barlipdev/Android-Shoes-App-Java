package com.skowronsky.snkrs.database;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity
public class Favorite {
    @PrimaryKey(autoGenerate = true)
    public long idFavoriteShoes;
    public long idShoes;
    public long idBase;
    public double size;

    public Favorite(){};

    @Ignore
    public Favorite(Shoes shoes, double size, long idBase){
        this.idShoes = shoes.idShoes;
        this.size = size;
        this.idBase = idBase;
    }
}