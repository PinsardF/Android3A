package com.example.projetmobile;

import com.example.projetmobile.Meuble;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface MeubleApi {

    @GET("meubles.json")
    Call<List<Meuble>> loadChanges();
}