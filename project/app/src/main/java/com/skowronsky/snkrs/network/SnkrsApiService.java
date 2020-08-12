package com.skowronsky.snkrs.network;

import com.skowronsky.snkrs.database.Brand;
import com.skowronsky.snkrs.database.BrandShoes;
import com.skowronsky.snkrs.database.Shoes;

import java.util.List;

import io.reactivex.rxjava3.core.Flowable;
import retrofit2.http.GET;

public interface SnkrsApiService {
    @GET("brands")
    Flowable<List<Brand>> getBrands();

    @GET("shoes")
    Flowable<List<Shoes>> getShoes();

}
