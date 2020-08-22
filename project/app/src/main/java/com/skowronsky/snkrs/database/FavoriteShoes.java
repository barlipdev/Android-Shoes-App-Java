package com.skowronsky.snkrs.database;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

public class FavoriteShoes {
    @Embedded
    public Favorite favorite;
    @Relation(
            entity = Shoes.class,
            parentColumn = "idShoes",
            entityColumn = "idShoes")
    public BrandShoes brandShoes;

    @Relation(
            entity = Base.class,
            parentColumn = "idBase",
            entityColumn = "idBase")
    public BaseShoes baseShoes;

    @Relation(
            parentColumn = "idSize",
            entityColumn ="idSize")
    public SizeChart size;

}
