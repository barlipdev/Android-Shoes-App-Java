package com.skowronsky.snkrs.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.Transaction;

import java.util.List;

@Dao
public interface BaseShoesDao {
    @Transaction
    @Query("SELECT * FROM base")
    LiveData<List<BaseShoes>> getAllBaseShoes();
}
