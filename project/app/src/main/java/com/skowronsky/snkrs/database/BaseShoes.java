package com.skowronsky.snkrs.database;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

public class BaseShoes {
    @Embedded
    public Base base;
    @Relation(
            entity = Shoes.class,
            parentColumn = "idShoes",
            entityColumn ="idShoes")
    public BrandShoes brandShoes;

    @Relation(
            parentColumn = "idSize",
            entityColumn ="idSize")
    public SizeChart size;

    @Relation(
            parentColumn = "idHiddenSize",
            entityColumn ="idSize")
    public SizeChart hiddenSize;
}
