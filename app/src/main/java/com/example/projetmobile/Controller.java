package com.example.projetmobile;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Controller  {

    static final String BASE_URL = "https://pinsardf.github.io/FakeApiData/";
    private final MainActivity activity;

    public Controller(MainActivity mainActivity) {
        this.activity = mainActivity;
    }

    public void start() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        MeubleApi meubleAPI = retrofit.create(MeubleApi.class);

        Call<List<Meuble>> call = meubleAPI.loadChanges();
        call.enqueue(new Callback<List<Meuble>>() {
            @Override
            public void onResponse(Call<List<Meuble>> call, Response<List<Meuble>> response) {
                List<Meuble> meubleList = response.body();
                activity.showList(meubleList);
            }

            @Override
            public void onFailure(Call<List<Meuble>> call, Throwable t) {
                Log.d("ERROR", "Api error");
            }
        });

    }
}