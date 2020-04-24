package com.skowronsky.snkrs.model;

import java.io.Serializable;

public class FavoriteShoes implements Serializable {
    int idFavorite;
    double size;

    public FavoriteShoes(int idFavorite, double size){
        this.idFavorite = idFavorite;
        this.size = size;
    }

    public int getIdShoes() {
        return idFavorite;
    }

    public double getSize() {
        return size;
    }
}


