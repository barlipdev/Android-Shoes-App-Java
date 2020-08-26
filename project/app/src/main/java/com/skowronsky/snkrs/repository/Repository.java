package com.skowronsky.snkrs.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.skowronsky.snkrs.database.Base;
import com.skowronsky.snkrs.database.BaseDao;
import com.skowronsky.snkrs.database.BaseShoes;
import com.skowronsky.snkrs.database.BaseShoesDao;
import com.skowronsky.snkrs.database.Brand;
import com.skowronsky.snkrs.database.BrandDao;
import com.skowronsky.snkrs.database.BrandShoes;
import com.skowronsky.snkrs.database.BrandShoesDao;
import com.skowronsky.snkrs.database.BrandSize;
import com.skowronsky.snkrs.database.BrandSizeDao;
import com.skowronsky.snkrs.database.Favorite;
import com.skowronsky.snkrs.database.FavoriteDao;
import com.skowronsky.snkrs.database.FavoriteShoes;
import com.skowronsky.snkrs.database.FavoriteShoesDao;
import com.skowronsky.snkrs.database.Shoes;
import com.skowronsky.snkrs.database.ShoesDao;
import com.skowronsky.snkrs.database.SizeChart;
import com.skowronsky.snkrs.database.SizeChartDao;
import com.skowronsky.snkrs.database.SneakersDatabase;

import java.util.List;

/**
 * Klasa w której znajdują się metody pośredniczące między lokalną bazą danych a aplikacją
 */
public class Repository {
    private BrandDao mBrandDao;
    private LiveData<List<Brand>> mAllBrands;

    private SizeChartDao mSizeChartDao;
    private LiveData<List<SizeChart>> mAllSizeChart;

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

    private BrandShoesDao mBrandShoesDao;
    private LiveData<List<BrandShoes>> mAllBrandShoes;

    private BrandSizeDao mBrandSizeDao;
    private LiveData<List<BrandSize>> mAllBrandSizeChart;

    private MutableLiveData<Shoes> shoe;


    public Repository(Application application){
        SneakersDatabase db = SneakersDatabase.getDatabase(application);
        mBrandDao = db.brandDao();
        mAllBrands = mBrandDao.getAll();

        mSizeChartDao = db.sizeChartDao();
        mAllSizeChart = mSizeChartDao.getAll();

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

        mBrandShoesDao = db.brandShoesDao();
        mAllBrandShoes = mBrandShoesDao.getAllBrandShoes();

        mBrandSizeDao = db.brandSizeChartDao();
        mAllBrandSizeChart = mBrandSizeDao.getAllBrandSizeChart();

        shoe = new MutableLiveData<>();
    }
    public LiveData<List<SizeChart>> getAllSizeChart(){
        return mAllSizeChart;
    }


    public void insertAllSizeChart(List<SizeChart> sizeCharts){
        SneakersDatabase.databaseWriteExecutor.execute(() -> {
            for (SizeChart item :
                    sizeCharts) {
                item.assignValue();
            }
            mSizeChartDao.insertAll(sizeCharts);
        });
    }

    public LiveData<List<FavoriteShoes>> getAllFavoriteShoes(){
        return mAllFavoriteShoes;
    }

    public LiveData<List<Favorite>> getAllFavorite(){
        return mAllFavorite;
    }

    /**
     * Metoda odpowiadająca za dodanie ulubionego buta do bazy danych
     * @param favorite ulubiony but
     */
    public void insertFavorite(Favorite favorite){
        SneakersDatabase.databaseWriteExecutor.execute(() -> {
            mFavoriteDao.insert(favorite);
        });
    }

    /**
     * Metoda odpowiadająca za dodanie listy ulubionych butów do bazy danych
     * @param favorite liczba ulubionych butów
     */
    public void insertAllFavorite(List<Favorite> favorite){
        SneakersDatabase.databaseWriteExecutor.execute(() -> {
            for (Favorite item :
                    favorite) {
                item.assignValues();
            }
            mFavoriteDao.insertAll(favorite);
        });
    }

    /**
     * Metoda odpowiadająca za usunięcie wszystkich ulubionych butów uzytkownika
     */
    public void deleteAllFavorites(){
        SneakersDatabase.databaseWriteExecutor.execute(() -> {
            mFavoriteDao.deleteAll();
        });
    }

    /**
     * Metoda odpowiadająca za usunięcie przekzanego ulubionego buta
     * @param favorite ulubiony but
     */
    public void deleteFavorite(Favorite favorite){
        SneakersDatabase.databaseWriteExecutor.execute(() -> {
            mFavoriteDao.delete(favorite);
        });
    }



    public LiveData<List<BaseShoes>> getAllBaseShoes(){
        return mAllBaseShoes;
    }

    public LiveData<List<BrandSize>> getAllBrandSizeChart(){
        return mAllBrandSizeChart;
    }

    public LiveData<List<BrandShoes>> getAllBrandShoes(){
        return mAllBrandShoes;
    }

    public LiveData<BaseShoes> getBaseShoes(int idBase){
        return mBaseShoesDao.getBaseShoes(idBase);
    }

    public LiveData<List<Brand>> getAllBrands(){
        return mAllBrands;
    }

    /**
     * Metoda odpowiadająca za dodanie marki do bazy danych
     * @param brand marka buta
     */
    public void insertBrand(Brand brand){
        SneakersDatabase.databaseWriteExecutor.execute(() -> {
            mBrandDao.insert(brand);
        });
    }

    /**
     * Metoda odpowiadająca za dodanie listy marek do bazy danych
     * @param brands lista marek butów
     */
    public void insertAllBrands(List<Brand> brands){
        SneakersDatabase.databaseWriteExecutor.execute(() -> {
            mBrandDao.insertAll(brands);
        });
    }

    /**
     * Metoda odpowiadająca za usunięcie z bazy danych marki przekazanej przez parametr
     * @param brand marka buta
     */
    public void delete(Brand brand){
        SneakersDatabase.databaseWriteExecutor.execute(() -> {
            mBrandDao.delete(brand);
        });
    }

    /**
     * Metoda odpowiadająca za usunięcie wszystkich marek butów z bazy danych
     */
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

    /**
     * Metoda odpowiadająca za dodanie buta do bazy danych
     * @param shoes but który ma zostać dodany
     */
    public void insertShoes(Shoes shoes){
        SneakersDatabase.databaseWriteExecutor.execute(() -> {
            mShoesDao.insert(shoes);
        });
    }

    /**
     * Metoda odpowiadająca za dodanie listy butów do bazy danych
     * @param shoes lista butów
     */
    public void insertAllShoes(List<Shoes> shoes){
        SneakersDatabase.databaseWriteExecutor.execute(() -> {
            for (Shoes item :
                    shoes) {
                item.assignValues();
            }
            mShoesDao.insertAll(shoes);
        });
    }

    public void insertAllBases(List<Base> bases){
        SneakersDatabase.databaseWriteExecutor.execute(() -> {
            for (Base item :
                    bases) {
                item.assignValues();
            }
            //TODO insertAllBaseShoes
            mBaseDao.insertAll(bases);
        });
    }


    /**
     * Metoda odpowiadająca za usuniecie z bazy danych buta przekazanego przez parametr
     * @param shoes but który ma zostać usunięty
     */
    public void delete(Shoes shoes){
        SneakersDatabase.databaseWriteExecutor.execute(() -> {
            mShoesDao.delete(shoes);
        });
    }

    /**
     * Metoda odpowiadająca za usunięcie wszystkich butów z bazy danych
     */
    public void deleteAllShoes(){
        SneakersDatabase.databaseWriteExecutor.execute(() -> {
            mShoesDao.deleteAll();
        });
    }

    public LiveData<List<Base>> getAllBase(){
        return mAllBase;
    }

    /**
     * Metoda odpowiadająca za dodanie bazy buta do bazy danych
     * @param base baza buta która ma zostać dodana
     */
    public void insertBase(Base base){
        SneakersDatabase.databaseWriteExecutor.execute(() -> {
            mBaseDao.insert(base);
        });
    }

    /**
     * Metoda odpowiadająca za usunięcie z bazy danych bazy która jest przekazana przez parametr
     * @param base baza która ma zostać usunięta
     */
    public void delete(Base base){
        SneakersDatabase.databaseWriteExecutor.execute(() -> {
            mBaseDao.delete(base);
        });
    }

    /**
     * Metoda odpowiadająca za usunięcie wszystkich baz z bazy danych
     */
    public void deleteAllBase(){
        SneakersDatabase.databaseWriteExecutor.execute(() -> {
            mBaseDao.deleteAll();
        });
    }

//TODO get brandName
//    public LiveData<List<Shoes>> getShoesByBrandName(String brandName){
//            return mShoesDao.getShoesByBrandName(brandName);
//    }

    public LiveData<String> getBrandByName(String brandName){
        return mBrandDao.getBrandByName(brandName);
    }

    public void setShoe(MutableLiveData<Shoes> shoe){
        this.shoe = shoe;
    }

    public MutableLiveData<Shoes> getShoe(){
        return shoe;
    }

}
