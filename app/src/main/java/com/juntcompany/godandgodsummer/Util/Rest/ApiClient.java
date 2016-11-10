package com.juntcompany.godandgodsummer.Util.Rest;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by EOM on 2016-08-02.
 */
public class ApiClient {
    //public static final String BASE_URL = "http://143.248.136.196:8080/";
    //public static final String BASE_URL = "http://218.158.70.90:8080/";
    public static final String BASE_URL = "http://35.162.211.3/";
    private static Retrofit retrofit = null;

    Gson gson = new GsonBuilder()
            .excludeFieldsWithoutExposeAnnotation()
            .create();

    public static Retrofit getClient() {
        if (retrofit==null) {
            Log.i("apiclient", "ok");
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
