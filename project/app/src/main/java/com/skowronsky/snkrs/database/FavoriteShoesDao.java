package com.skowronsky.snkrs.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.Transaction;

import java.util.List;

@Dao
public interface FavoriteShoesDao {
    @Transaction
    @Query("SELECT * FROM favorite")
    LiveData<List<FavoriteShoes>> getAllFavoriteShoes();

}
