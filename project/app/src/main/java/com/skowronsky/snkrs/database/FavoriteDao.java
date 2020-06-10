package com.skowronsky.snkrs.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface FavoriteDao {
    @Query("select * from favorite")
    LiveData<List<Favorite>> getAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Favorite favorite);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<Favorite> favoriteList);

    @Delete
    void delete(Favorite favorite);

    @Query("DELETE FROM favorite")
    void deleteAll();

}
