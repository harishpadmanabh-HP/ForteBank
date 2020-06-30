package com.hp.fortebank.Retro;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Retro {
    // create a method which returns our api interface class
    public Apis getApi() {
        //logging requests and responses in verbose
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();
        //build retrofit object
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://srishti-systems.info/projects/ForteBank/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client).build();

        //connect api class with this builder

        Apis apis = retrofit.create(Apis.class);
        return apis;
    }
}
