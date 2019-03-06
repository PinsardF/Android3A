package com.example.projetmobile.model;

import android.util.EventLogTags;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class Meuble {
    private String nom;
    private String nature;
    private String type;
    private String imageurl;
    private String environnement;
    private String description;

    public String getNom() {
        return nom;
    }

    public String getNature() {
        return nature;
    }

    public String getImageurl() {
        return imageurl;
    }

    public String getType() {
        return type;
    }

    public String getEnv() {
        return environnement;
    }

    public String getDesc() { return description; }
}
