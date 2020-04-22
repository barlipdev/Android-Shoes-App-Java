package com.skowronsky.snkrs.database;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity()
public class Shoes {
    @PrimaryKey
    public int id_shoes;

    public String brand_name;

    public String modelName;

    public double factor;

    public String image;
}
