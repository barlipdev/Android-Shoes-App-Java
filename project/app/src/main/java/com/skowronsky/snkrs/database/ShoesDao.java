package com.skowronsky.snkrs.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ShoesDao {
    @Query("select * from shoes")
    LiveData<List<Shoes>> getAll();

    @Query("select * from shoes where idShoes=:idShoes")
    LiveData<Shoes> getShoes(int idShoes);

    @Query("select * from shoes where idBrand=:idBrand")
    LiveData<List<Shoes>> getShoesByIdBrand(long idBrand);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<Shoes> shoes);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Shoes shoes);

    @Delete
    void delete(Shoes shoes);

    @Query("DELETE FROM shoes")
    void deleteAll();
}
