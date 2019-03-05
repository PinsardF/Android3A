package com.example.projetmobile;

import android.content.SharedPreferences;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Controller  {

    static final String BASE_URL = "https://pinsardf.github.io/FakeApiData/";
    private final MainActivity activity;
    private final String NOMBRE_ELEMENTS = "NOMBRE_ELEMENTS";
    private final String ELEMENTS = "ELEMENTS";
    SharedPreferences cache;

    public Controller(MainActivity mainActivity, SharedPreferences sharedPreferences) {
        this.activity = mainActivity;
        this.cache = sharedPreferences;
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

        if(DataInDatabase()) {
            List<Meuble> listemeubles = getListFromDatabase();
            activity.showList(listemeubles);
        }
        else{
            CallAPI(meubleAPI);
        }
    }

    private List<Meuble> getListFromDatabase() {
        Gson gsonList = new Gson();
        String json = cache.getString(ELEMENTS,null);
        return gsonList.fromJson(json,new TypeToken<List<Meuble>>(){}.getType());
    }

    private boolean DataInDatabase() {
        return cache.contains(NOMBRE_ELEMENTS);
    }
    //

    void CallAPI(MeubleApi meubleAPI){
        Call<List<Meuble>> call = meubleAPI.loadChanges();
        call.enqueue(new Callback<List<Meuble>>() {
            @Override
            public void onResponse(Call<List<Meuble>> call, Response<List<Meuble>> response) {
                List<Meuble> meubleList = response.body();
                Gson gson = new Gson();
                String json = gson.toJson(meubleList);
                cache.edit().putString(ELEMENTS,json).putInt(NOMBRE_ELEMENTS,meubleList.size()).apply();
                activity.showList(meubleList);
            }

            @Override
            public void onFailure(Call<List<Meuble>> call, Throwable t) {
                Log.d("ERROR", "Api error");
            }
        });
    }
}