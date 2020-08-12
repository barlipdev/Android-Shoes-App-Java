package com.skowronsky.snkrs.database;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

public class BaseShoes {
    @Embedded
    public Base base;
    @Relation(
            parentColumn = "idShoes",
            entityColumn ="idShoes")
    public Shoes shoes;
}
