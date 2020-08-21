package com.skowronsky.snkrs.database;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity
public class Favorite {
    @PrimaryKey
    @SerializedName("idFavorite")
    @Expose
    private long idFavorite;
    private long idShoes;
    private long idBase;
    private long idSize;

    @SerializedName("shoes")
    @Expose
    @Ignore
    private Shoes shoes;

    @SerializedName("base")
    @Expose
    @Ignore
    private Base base;

    @SerializedName("size")
    @Expose
    @Ignore
    private SizeChart size;

    public Favorite(){ }

    @Ignore
    public Favorite(long idFavorite, long idShoes, long idBase, long idSize, Shoes shoes, Base base, SizeChart size) {
        this.idFavorite = idFavorite;
        this.idShoes = idShoes;
        this.idBase = idBase;
        this.idSize = idSize;
        this.shoes = shoes;
        this.base = base;
        this.size = size;
    }

    @Ignore
    public Favorite(Shoes shoes, long idSize, long idBase){
        this.idShoes = shoes.getIdShoes();
        this.idSize = idSize;
        this.idBase = idBase;
    }

    public void assignValues(){
        idShoes = shoes.getIdShoes();
        idBase = base.getIdBase();
        idSize = size.getIdSize();
    }

    public long getIdFavorite() {
        return idFavorite;
    }

    public void setIdFavorite(long idFavorite) {
        this.idFavorite = idFavorite;
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

    public long getIdSize() {
        return idSize;
    }

    public void setIdSize(long idSize) {
        this.idSize = idSize;
    }

    public Shoes getShoes() {
        return shoes;
    }

    public void setShoes(Shoes shoes) {
        this.shoes = shoes;
    }

    public Base getBase() {
        return base;
    }

    public void setBase(Base base) {
        this.base = base;
    }

    public SizeChart getSize() {
        return size;
    }

    public void setSize(SizeChart size) {
        this.size = size;
    }
}