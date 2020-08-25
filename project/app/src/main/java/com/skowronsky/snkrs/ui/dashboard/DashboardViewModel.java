package com.skowronsky.snkrs.ui.dashboard;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.firebase.auth.FirebaseAuth;
import com.skowronsky.snkrs.database.Base;
import com.skowronsky.snkrs.database.BaseShoes;
import com.skowronsky.snkrs.database.Brand;
import com.skowronsky.snkrs.database.BrandShoes;
import com.skowronsky.snkrs.database.BrandSizeChart;
import com.skowronsky.snkrs.database.Favorite;
import com.skowronsky.snkrs.database.FavoriteShoes;
import com.skowronsky.snkrs.database.Shoes;
import com.skowronsky.snkrs.database.SizeChart;
import com.skowronsky.snkrs.network.ApiClient;
import com.skowronsky.snkrs.network.SnkrsApiService;
import com.skowronsky.snkrs.repository.Repository;

import java.util.List;

import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import retrofit2.Retrofit;

public class DashboardViewModel extends AndroidViewModel {

    private MutableLiveData<Boolean> eventConnect;
    private MutableLiveData<Boolean> eventDisconnect;

    private Repository repository;
    private LiveData<List<Shoes>> allShoes;
    private LiveData<List<Base>> allBases;
    private LiveData<List<SizeChart>> allSizeChart;
    private LiveData<List<BaseShoes>> allBaseShoes;
    private LiveData<List<FavoriteShoes>> allFavoriteShoes;
    private LiveData<List<BrandShoes>> allBrandShoes;
    private LiveData<List<BrandSizeChart>> allBrandSizeChart;

    public MutableLiveData<String> text = new MutableLiveData<>();

    public List<Shoes> shoesList = null;

    public String siema = "siema";

    SnkrsApiService snkrsApiService;
    FirebaseAuth mAuth;

    public DashboardViewModel(@NonNull Application application) {
        super(application);
        repository = new Repository(application);
        allShoes = repository.getAllShoes();
        allBases = repository.getAllBase();
        allSizeChart = repository.getAllSizeChart();
        allBaseShoes = repository.getAllBaseShoes();
        allFavoriteShoes = repository.getAllFavoriteShoes();
        allBrandShoes = repository.getAllBrandShoes();
        allBrandSizeChart = repository.getAllBrandSizeChart();

        Retrofit apiClient = ApiClient.getInstance();
        mAuth = FirebaseAuth.getInstance();
        snkrsApiService = apiClient.create(SnkrsApiService.class);

      //Log.i("Snkrs","works");
        text.setValue("Siema\nCo tam?");
    }

    public void connect(){





        snkrsApiService.getBrands()
                .toObservable()
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<List<Brand>>() {
                    @Override
                    public void onSubscribe(@io.reactivex.rxjava3.annotations.NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@io.reactivex.rxjava3.annotations.NonNull List<Brand> brands) {
                        insertAllBrands(brands);
                    }

                    @Override
                    public void onError(@io.reactivex.rxjava3.annotations.NonNull Throwable e) {
                        Log.i("Snkrs","err");

                    }

                    @Override
                    public void onComplete() {

                    }
                });

        snkrsApiService.getSizeChart()
                .toObservable()
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<List<SizeChart>>() {
                    @Override
                    public void onSubscribe(@io.reactivex.rxjava3.annotations.NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@io.reactivex.rxjava3.annotations.NonNull List<SizeChart> sizeCharts) {
                        insertAllSizeChart(sizeCharts);
                    }

                    @Override
                    public void onError(@io.reactivex.rxjava3.annotations.NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

        snkrsApiService.getShoes()
                .toObservable()
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<List<Shoes>>() {
                    @Override
                    public void onSubscribe(@io.reactivex.rxjava3.annotations.NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@io.reactivex.rxjava3.annotations.NonNull List<Shoes> shoes) {
                        insertAllShoes(shoes);
                    }

                    @Override
                    public void onError(@io.reactivex.rxjava3.annotations.NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

        snkrsApiService.getBase(mAuth.getUid())
                .toObservable()
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<List<Base>>() {
                    @Override
                    public void onSubscribe(@io.reactivex.rxjava3.annotations.NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@io.reactivex.rxjava3.annotations.NonNull List<Base> bases) {
                        insertAllBases(bases);
                    }

                    @Override
                    public void onError(@io.reactivex.rxjava3.annotations.NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

        snkrsApiService.getFavorite(mAuth.getUid())
                .toObservable()
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<List<Favorite>>() {
                    @Override
                    public void onSubscribe(@io.reactivex.rxjava3.annotations.NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@io.reactivex.rxjava3.annotations.NonNull List<Favorite> favorites) {
                        repository.insertAllFavorite(favorites);
                    }

                    @Override
                    public void onError(@io.reactivex.rxjava3.annotations.NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    public MutableLiveData<Boolean> getEventConnect(){
        if(eventConnect == null)
            eventConnect = new MutableLiveData<Boolean>();
        return eventConnect;
    }

    void deleteAllBase(){
        repository.deleteAllBase();
    }

    public void setEventConnect(){
        eventConnect.setValue(true);
    }
    public void connectFinished(){
        eventConnect.setValue(false);
    }

    public LiveData<List<Shoes>> getAllShoes(){
        return allShoes;
    }
    public LiveData<List<Base>> getAllBases(){
        return allBases;
    }
    public LiveData<List<SizeChart>> getAllSizeChart(){
        return allSizeChart;
    }

    public LiveData<List<BaseShoes>> getAllBaseShoes(){
        return allBaseShoes;
    }
    public LiveData<List<FavoriteShoes>> getAllFavoriteShoes(){
        return allFavoriteShoes;
    }

    public LiveData<List<BrandShoes>> getAllBrandShoes(){
        return allBrandShoes;
    }
    public LiveData<List<BrandSizeChart>> getAllBrandSizeChart(){
        return allBrandSizeChart;
    }




    public MutableLiveData<Boolean> getEventDisconnect() {
        if(eventDisconnect == null)
            eventDisconnect = new MutableLiveData<Boolean>();
        return eventDisconnect;
    }

    public LiveData<Shoes> getShoes(int shoesId){
        return repository.getShoes(shoesId);
    }

    public void insert(Base base){
        repository.insertBase(base);
    }

    public void insertAllBases(List<Base> bases) {
        repository.insertAllBases(bases);
    }

    public void insertAllBrands(List<Brand> brands){
        repository.insertAllBrands(brands);
    }
    public void insertAllSizeChart(List<SizeChart> sizeChart){
        repository.insertAllSizeChart(sizeChart);
    }

    public void insertAllShoes(List<Shoes> shoes){
        repository.insertAllShoes(shoes);
    }

    public void insert(Brand brand){
        repository.insertBrand(brand);
    }
    public void deleteBrand(Brand brand){
        repository.delete(brand);
    }

    public void deleteAllBrand(){
        repository.deleteAllBrands();
    }

    public void deleteAllShoes(){
        repository.deleteAllShoes();
    }

    public void setEventDisconnect(){
        eventDisconnect.setValue(true);
    }
    public void disconnectFinished(){
        eventDisconnect.setValue(false);
    }
}