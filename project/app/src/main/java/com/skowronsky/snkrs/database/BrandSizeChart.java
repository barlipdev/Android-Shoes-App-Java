package com.skowronsky.snkrs.database;

import androidx.room.Embedded;
import androidx.room.Relation;

public class BrandSizeChart {

    @Embedded
    public SizeChart sizeChart;
    @Relation(
            parentColumn = "idBrand",
            entityColumn = "idBrand")
    public Brand brand;
}
