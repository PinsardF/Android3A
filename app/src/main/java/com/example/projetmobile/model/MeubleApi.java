package com.example.projetmobile.model;

import com.example.projetmobile.model.Meuble;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface MeubleApi {

    @GET("meubles.json")
    Call<List<Meuble>> loadChanges();
}