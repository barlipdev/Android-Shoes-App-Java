package com.skowronsky.snkrs.database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Brand.class,Shoes.class}, version = 1)
public abstract class SneakersDatabase extends RoomDatabase {
    public abstract BrandDao brandDao();
    public abstract ShoesDao shoesDao();

    private static volatile SneakersDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;

    public static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static SneakersDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (SneakersDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            SneakersDatabase.class, "sneakers_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }

}
