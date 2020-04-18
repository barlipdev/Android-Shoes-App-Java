package com.skowronsky.snkrs.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.skowronsky.snkrs.database.Brand;
import com.skowronsky.snkrs.database.BrandDao;
import com.skowronsky.snkrs.database.Shoes;
import com.skowronsky.snkrs.database.ShoesDao;
import com.skowronsky.snkrs.database.SneakersDatabase;
import com.skowronsky.snkrs.storage.Storage;

import java.util.List;

public class Repository {
    private BrandDao mBrandDao;
    private LiveData<List<Brand>> mAllBrands;

    private ShoesDao mShoesDao;
    private LiveData<List<Shoes>> mAllShoes;

    public Repository(Application application){
        SneakersDatabase db = SneakersDatabase.getDatabase(application);
        mBrandDao = db.brandDao();
        mAllBrands = mBrandDao.getAll();

        mShoesDao = db.shoesDao();
        mAllShoes = mShoesDao.getAll();
    }

    public LiveData<List<Brand>> getAllBrands(){
        return mAllBrands;
    }

    public void insertBrand(Brand brand){
        SneakersDatabase.databaseWriteExecutor.execute(() -> {
            mBrandDao.insert(brand);
        });
    }
    public void insertAllBrands(List<Brand> brands){
        SneakersDatabase.databaseWriteExecutor.execute(() -> {
            mBrandDao.insertAll(brands);
        });
    }

    public void delete(Brand brand){
        SneakersDatabase.databaseWriteExecutor.execute(() -> {
            mBrandDao.delete(brand);
        });
    }

    public void deleteAllBrands(){
        SneakersDatabase.databaseWriteExecutor.execute(() -> {
            mBrandDao.deleteAll();
        });
    }

    public LiveData<List<Shoes>> getAllShoes(){
        return mAllShoes;
    }

    public void insertShoes(Shoes shoes){
        SneakersDatabase.databaseWriteExecutor.execute(() -> {
            mShoesDao.insert(shoes);
        });
    }
    public void insertAllShoes(List<Shoes> shoes){
        SneakersDatabase.databaseWriteExecutor.execute(() -> {
            mShoesDao.insertAll(shoes);
        });
    }

    public void delete(Shoes shoes){
        SneakersDatabase.databaseWriteExecutor.execute(() -> {
            mShoesDao.delete(shoes);
        });
    }

    public void deleteAllShoes(){
        SneakersDatabase.databaseWriteExecutor.execute(() -> {
            mShoesDao.deleteAll();
        });
    }

}
