package com.skowronsky.snkrs.database;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Brand {

    @PrimaryKey
    public int id_brand;

    public String brand_name;

    public String image;

}
