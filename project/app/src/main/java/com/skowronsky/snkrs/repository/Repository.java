package com.skowronsky.snkrs.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.skowronsky.snkrs.database.Base;
import com.skowronsky.snkrs.database.BaseDao;
import com.skowronsky.snkrs.database.BaseShoes;
import com.skowronsky.snkrs.database.BaseShoesDao;
import com.skowronsky.snkrs.database.Brand;
import com.skowronsky.snkrs.database.BrandDao;
import com.skowronsky.snkrs.database.Favorite;
import com.skowronsky.snkrs.database.FavoriteDao;
import com.skowronsky.snkrs.database.FavoriteShoes;
import com.skowronsky.snkrs.database.FavoriteShoesDao;
import com.skowronsky.snkrs.database.Shoes;
import com.skowronsky.snkrs.database.ShoesDao;
import com.skowronsky.snkrs.database.SneakersDatabase;

import java.util.List;

public class Repository {
    private BrandDao mBrandDao;
    private LiveData<List<Brand>> mAllBrands;

    private ShoesDao mShoesDao;
    private LiveData<List<Shoes>> mAllShoes;

    private BaseDao mBaseDao;
    private LiveData<List<Base>> mAllBase;

    private BaseShoesDao mBaseShoesDao;
    private LiveData<List<BaseShoes>> mAllBaseShoes;

    private FavoriteDao mFavoriteDao;
    private LiveData<List<Favorite>> mAllFavorite;

    private FavoriteShoesDao mFavoriteShoesDao;
    private LiveData<List<FavoriteShoes>> mAllFavoriteShoes;


    public Repository(Application application){
        SneakersDatabase db = SneakersDatabase.getDatabase(application);
        mBrandDao = db.brandDao();
        mAllBrands = mBrandDao.getAll();

        mShoesDao = db.shoesDao();
        mAllShoes = mShoesDao.getAll();

        mBaseDao = db.baseDao();
        mAllBase = mBaseDao.getAll();

        mBaseShoesDao = db.baseShoesDao();
        mAllBaseShoes = mBaseShoesDao.getAllBaseShoes();

        mFavoriteDao = db.favoriteDao();
        mAllFavorite = mFavoriteDao.getAll();

        mFavoriteShoesDao = db.favoriteShoesDao();
        mAllFavoriteShoes = mFavoriteShoesDao.getAllFavoriteShoes();
    }

    public LiveData<List<FavoriteShoes>> getAllFavoriteShoes(){
        return mAllFavoriteShoes;
    }

    public LiveData<List<Favorite>> getAllFavorite(){
        return mAllFavorite;
    }


    public void insertFavorite(Favorite favorite){
        SneakersDatabase.databaseWriteExecutor.execute(() -> {
            mFavoriteDao.insert(favorite);
        });
    }

    public void insertAllFavorite(List<Favorite> favorite){
        SneakersDatabase.databaseWriteExecutor.execute(() -> {
            mFavoriteDao.insertAll(favorite);
        });
    }

    public void deleteAllFavorites(){
        SneakersDatabase.databaseWriteExecutor.execute(() -> {
            mFavoriteDao.deleteAll();
        });
    }

    public void deleteFavorite(Favorite favorite){
        SneakersDatabase.databaseWriteExecutor.execute(() -> {
            mFavoriteDao.delete(favorite);
        });
    }

    public LiveData<List<BaseShoes>> getAllBaseShoes(){
        return mAllBaseShoes;
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

    public LiveData<Shoes> getShoes(int idShoes){
        return mShoesDao.getShoes(idShoes);
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

    public LiveData<List<Base>> getAllBase(){
        return mAllBase;
    }

    public void insertBase(Base base){
        SneakersDatabase.databaseWriteExecutor.execute(() -> {
            mBaseDao.insert(base);
        });
    }

    public void delete(Base base){
        SneakersDatabase.databaseWriteExecutor.execute(() -> {
            mBaseDao.delete(base);
        });
    }

    public void deleteAllBase(){
        SneakersDatabase.databaseWriteExecutor.execute(() -> {
            mBaseDao.deleteAll();
        });
    }

}
