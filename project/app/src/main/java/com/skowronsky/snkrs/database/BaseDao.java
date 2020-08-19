package com.skowronsky.snkrs.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface BaseDao {
    @Query("select * from base")
    LiveData<List<Base>> getAll();


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Base base);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<Base> bases);

    @Delete
    void delete(Base base);

    @Query("DELETE FROM base")
    void deleteAll();
}
