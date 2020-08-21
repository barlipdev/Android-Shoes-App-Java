package com.skowronsky.snkrs.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.Transaction;

import java.util.List;

@Dao
public interface BrandShoesDao {
    @Transaction
    @Query("SELECT * FROM shoes")
    LiveData<List<BrandShoes>> getAllBrandShoes();

    @Transaction
    @Query("SELECT * FROM shoes where idBrand = :idBrand")
    LiveData<BrandShoes> getShoesByBrandId(long idBrand);
}
