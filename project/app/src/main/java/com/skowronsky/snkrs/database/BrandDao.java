package com.skowronsky.snkrs.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface BrandDao {
    @Query("select * from brand")
    LiveData<List<Brand>> getAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<Brand> brands);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Brand brand);

    @Delete
    void delete(Brand brand);

    @Query("DELETE FROM brand")
    void deleteAll();
}
