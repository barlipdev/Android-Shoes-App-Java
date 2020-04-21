package com.skowronsky.snkrs.database;

import androidx.room.Dao;
import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

public class BaseShoes {
    @Embedded
    public Base base;
    @Relation(
            parentColumn = "id_shoes",
            entityColumn ="id_shoes")
    public List<Shoes> bases;
//    public Base base;
//    public Shoes shoes;
}
