package com.skowronsky.snkrs.database;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity
public class Favorite {
    @PrimaryKey(autoGenerate = true)
    private long idFavoriteShoes;
    private long idShoes;
    private long idBase;
    private double size;

    public Favorite(){};

    @Ignore
    public Favorite(Shoes shoes, double size, long idBase){
        this.idShoes = shoes.idShoes;
        this.size = size;
        this.idBase = idBase;
    }

    public long getIdFavoriteShoes() {
        return idFavoriteShoes;
    }

    public void setIdFavoriteShoes(long idFavoriteShoes) {
        this.idFavoriteShoes = idFavoriteShoes;
    }

    public long getIdShoes() {
        return idShoes;
    }

    public void setIdShoes(long idShoes) {
        this.idShoes = idShoes;
    }

    public long getIdBase() {
        return idBase;
    }

    public void setIdBase(long idBase) {
        this.idBase = idBase;
    }

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }
}