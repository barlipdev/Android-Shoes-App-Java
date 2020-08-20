package com.skowronsky.snkrs.database;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity
public class Base {
    @PrimaryKey(autoGenerate = true)
    private long idBase;
    private long idShoes;
    private long idSize;
    private long idHiddenSize;

    @SerializedName("shoes")
    @Expose
    @Ignore
    private Shoes shoes;

    @SerializedName("size")
    @Expose
    @Ignore
    private SizeChart size;

    @SerializedName("hiddenSize")
    @Expose
    @Ignore
    private SizeChart hiddenSize;

    public Base(){};

    @Ignore
    public Base(long idBase, long idShoes, long idSize, long idHiddenSize, Shoes shoes, SizeChart size, SizeChart hiddenSize) {
        this.idBase = idBase;
        this.idShoes = idShoes;
        this.idSize = idSize;
        this.idHiddenSize = idHiddenSize;
        this.shoes = shoes;
        this.size = size;
        this.hiddenSize = hiddenSize;
    }

    public long getIdBase() {
        return idBase;
    }

    public void setIdBase(long idBase) {
        this.idBase = idBase;
    }

    public long getIdShoes() {
        return idShoes;
    }

    public void setIdShoes(long idShoes) {
        this.idShoes = idShoes;
    }

    public long getIdSize() {
        return idSize;
    }

    public void setIdSize(long idSize) {
        this.idSize = idSize;
    }

    public long getIdHiddenSize() {
        return idHiddenSize;
    }

    public void setIdHiddenSize(long idHiddenSize) {
        this.idHiddenSize = idHiddenSize;
    }

    public Shoes getShoes() {
        return shoes;
    }

    public void setShoes(Shoes shoes) {
        this.shoes = shoes;
    }

    public SizeChart getSize() {
        return size;
    }

    public void setSize(SizeChart size) {
        this.size = size;
    }

    public SizeChart getHiddenSize() {
        return hiddenSize;
    }

    public void setHiddenSize(SizeChart hiddenSize) {
        this.hiddenSize = hiddenSize;
    }
}
