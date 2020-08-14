package com.skowronsky.snkrs.database;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

public class FavoriteShoes {
    @Embedded
    public Favorite favorite;
    @Relation(
            parentColumn = "idShoes",
            entityColumn = "idShoes")
    public Shoes shoes;

    @Relation(
            parentColumn = "idBase",
            entityColumn = "idBase")
    public Base base;

}
