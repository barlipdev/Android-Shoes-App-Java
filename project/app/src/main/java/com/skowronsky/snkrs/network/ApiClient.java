package com.skowronsky.snkrs.network;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    private static Retrofit INSTANCE;

    public static Retrofit getInstance() {
        if(INSTANCE == null)
            INSTANCE = new Retrofit.Builder()
                    .baseUrl("http://10.0.2.2:8080/api/snkrs/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                    .build();
        return INSTANCE;
    }

    private ApiClient(){}
}
