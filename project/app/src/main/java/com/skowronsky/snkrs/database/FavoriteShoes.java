package com.skowronsky.snkrs.database;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

public class FavoriteShoes {
    @Embedded
    public Favorite favorite;
    @Relation(
            parentColumn = "id_shoes",
            entityColumn = "id_shoes")
    public Shoes shoes;

    @Relation(
            parentColumn = "id_base",
            entityColumn = "id_base")
    public Base base;

}
