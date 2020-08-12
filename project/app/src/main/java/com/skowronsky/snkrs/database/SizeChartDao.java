package com.skowronsky.snkrs.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface SizeChartDao {

    @Query("select * from size_chart")
    LiveData<List<SizeChart>> getAll();

    @Query("select * from size_chart where idSize=:idSize")
    LiveData<SizeChart> getSizeChart(int idSize);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<SizeChart> sizechart);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(SizeChart sizechart);

    @Delete
    void delete(SizeChart sizechart);

    @Query("DELETE FROM size_chart")
    void deleteAll();
}
