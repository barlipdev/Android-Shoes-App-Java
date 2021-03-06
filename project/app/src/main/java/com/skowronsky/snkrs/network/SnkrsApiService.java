package com.skowronsky.snkrs.network;

import com.skowronsky.snkrs.database.Base;
import com.skowronsky.snkrs.database.Brand;
import com.skowronsky.snkrs.database.Favorite;
import com.skowronsky.snkrs.database.Shoes;
import com.skowronsky.snkrs.database.SizeChart;

import java.util.List;

import io.reactivex.rxjava3.core.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface SnkrsApiService {
    @GET("brands")
    Flowable<List<Brand>> getBrands();

    @GET("shoes")
    Flowable<List<Shoes>> getShoes();

    @GET("sizechart")
    Flowable<List<SizeChart>> getSizeChart();

    @GET("baseshoes")
    Flowable<List<Base>> getBase(@Header("uid") String uid);

    @GET("favshoes")
    Flowable<List<Favorite>> getFavorite(@Header("uid") String uid);

}
