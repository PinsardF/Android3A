package com.example.projetmobile.view;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.projetmobile.model.Meuble;
import com.example.projetmobile.R;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.util.List;

public class SecondActivity extends AppCompatActivity {
    int button_state=0;
    Fragment firstFragment;
    Fragment secondFragment;
    Button left_button;
    Button right_button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        String json = getIntent().getStringExtra("meubleData");
        Gson gson = new Gson();
        Meuble meuble = gson.fromJson(json,Meuble.class);
        TextView name = findViewById(R.id.nom_ikeamon);
        ImageView pic = findViewById(R.id.image_ikeamon);
        name.setText(meuble.getNom());
        Picasso.with(this).load(meuble.getImageurl()).into(pic);

        left_button = findViewById(R.id.buttondesc);
        right_button = findViewById(R.id.buttoninfo);

        left_button.setText("Infos");
        right_button.setText("Desc.");
        left_button.setBackgroundColor(Color.parseColor("#70756f"));
        right_button.setBackgroundColor(Color.parseColor("#20b2aa"));

        Bundle bundle = new Bundle();
        Bundle bundle2 = new Bundle();

        String[] infos=new String[3];
        infos[0]=meuble.getType();
        infos[1]=meuble.getNature();
        infos[2]=meuble.getEnv();
        bundle.putStringArray("infos",infos);
        bundle2.putString("descbundle",meuble.getDesc()+" ");
        firstFragment = new FirstFragment();
        firstFragment.setArguments(bundle);
        secondFragment = new SecondFragment();
        secondFragment.setArguments(bundle2);
        getSupportFragmentManager().beginTransaction().add(R.id.fragment,firstFragment).commit();

    }

    public void button_desc(View view) {
        if(button_state==1){
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment,firstFragment)
                    .commit();
            button_state=0;
            left_button.setBackgroundColor(Color.parseColor("#70756f"));
            right_button.setBackgroundColor(Color.parseColor("#20b2aa"));
        }
    }

    public void button_info(View view) {
        if(button_state==0){
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment,secondFragment)
                    .commit();
            button_state=1;
            right_button.setBackgroundColor(Color.parseColor("#70756f"));
            left_button.setBackgroundColor(Color.parseColor("#20b2aa"));
        }
    }
}
