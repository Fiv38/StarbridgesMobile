package com.example.android.starbridges.network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by user on 5/8/2018.
 */

public class APIClient {
    public static String BASE_URL ="http://116.254.100.222:7000/";
    //public static String API_KEY ="59D3CFCA29DB8697C4962A36EEB653C8";
    private static Retrofit retrofit = null;

    public static Retrofit getClient(){

        Interceptor interceptor = new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {
                Request newRequest = chain.request().newBuilder().addHeader("Content-Type", "application/x-www-form-urlencoded").build();
                return chain.proceed(newRequest);
            }
        };



        //RequestBody body = RequestBody.create(okhttp3.MediaType.parse("text/plain; charset=utf-8"),"grant_type=password&username=handri.fajar&password=Pass1234.&client_id=ngAuthApp");

        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();


        //Log.d("DataLogin", client.toString());

        GsonBuilder gb = new GsonBuilder();
        gb.registerTypeAdapter(String.class, new StringConverter());
        gb.serializeNulls();
        Gson gson = gb.create();

        retrofit = new Retrofit.Builder()
                .baseUrl(APIClient.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(client)
                .build();

        return retrofit;

    }

    public static Retrofit inputAbsence(final String token){
        Interceptor interceptor = new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {
                Request newRequest = chain
                        .request()
                        .newBuilder()
                        .addHeader("Authorization", token)
                        .build();
                return chain.proceed(newRequest);
            }
        };

        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        //GsonBuilder gb = new GsonBuilder();
        //gb.registerTypeAdapter(String.class, new StringConverter());
        //gb.serializeNulls();
        //Gson gson = gb.create();

        retrofit = new Retrofit.Builder()
                .baseUrl(APIClient.BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit;
    }

    // used for get History
    public static Retrofit getHistory(final String token){
        Interceptor interceptor = new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {
                Request newRequest = chain
                        .request()
                        .newBuilder()
                        .addHeader("Authorization", token)
                        .build();
                return chain.proceed(newRequest);
            }
        };

        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        //GsonBuilder gb = new GsonBuilder();
        //gb.registerTypeAdapter(String.class, new StringConverter());
        //gb.serializeNulls();
        //Gson gson = gb.create();

        retrofit = new Retrofit.Builder()
                .baseUrl(APIClient.BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit;
    }

    // used for get Location
    public static Retrofit getLocationValue(final String token){
        Interceptor interceptor = new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {
                Request newRequest = chain
                        .request()
                        .newBuilder()
                        .addHeader("Authorization", token)
                        .build();
                return chain.proceed(newRequest);
            }
        };

        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        //GsonBuilder gb = new GsonBuilder();
        //gb.registerTypeAdapter(String.class, new StringConverter());
        //gb.serializeNulls();
        //Gson gson = gb.create();

        retrofit = new Retrofit.Builder()
                .baseUrl(APIClient.BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit;
    }

    //used from list correction
    public static Retrofit getListAttendanceCorrection(final String token){
        Interceptor interceptor = new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {
                Request newRequest = chain
                        .request()
                        .newBuilder()
                        .addHeader("Authorization", token)
                        .build();
                return chain.proceed(newRequest);
            }
        };

        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        GsonBuilder gb = new GsonBuilder();
        gb.registerTypeAdapter(String.class, new StringConverter());
        gb.serializeNulls();
        Gson gson = gb.create();

        retrofit = new Retrofit.Builder()
                .baseUrl(APIClient.BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit;
    }
}
