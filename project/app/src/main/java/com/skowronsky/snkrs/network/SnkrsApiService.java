package com.skowronsky.snkrs.network;

import com.skowronsky.snkrs.database.Brand;

import java.util.List;

import retrofit2.http.GET;

public interface SnkrsApiService {
    @GET("/brands")
    List<Brand> getBrands();
}
