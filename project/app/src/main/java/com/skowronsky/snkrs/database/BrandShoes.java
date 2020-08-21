package com.skowronsky.snkrs.database;

import androidx.room.Embedded;
import androidx.room.Relation;

public class BrandShoes {

    @Embedded
    public Shoes shoes;

    @Relation(
            parentColumn = "idBrand",
            entityColumn ="idBrand")
    public Brand brand;
}
