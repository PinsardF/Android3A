package com.example.projetmobile;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.util.List;

public class SecondActivity extends AppCompatActivity {
    private static List<Meuble> listeMeubles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        String json = getIntent().getStringExtra("meubleData");
        Gson gson = new Gson();
        Meuble meuble = gson.fromJson(json,Meuble.class);
        TextView name = findViewById(R.id.nom_ikeamon);
        TextView typedesc = findViewById(R.id.type_ikeamon);
        TextView naturedesc = findViewById(R.id.nature_ikeamon);
        ImageView pic = findViewById(R.id.image_ikeamon);
        name.setText(meuble.getNom());
        typedesc.setText("Type :"+meuble.getType());
        naturedesc.setText("Nature :"+meuble.getNature());
        Picasso.with(this).load(meuble.getImageurl()).into(pic);
    }
}
