package com.example.projetmobile.view;

import android.os.Bundle;
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
    private static List<Meuble> listeMeubles;
    int button_state=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        String json = getIntent().getStringExtra("meubleData");
        Gson gson = new Gson();
        Meuble meuble = gson.fromJson(json,Meuble.class);
        TextView name = findViewById(R.id.nom_ikeamon);
        //TextView typedesc = findViewById(R.id.type_ikeamon);
        //TextView naturedesc = findViewById(R.id.nature_ikeamon);
        //TextView envdesc = findViewById(R.id.env_ikeamon);
        ImageView pic = findViewById(R.id.image_ikeamon);
        name.setText(meuble.getNom());
        //typedesc.setText("Type : "+meuble.getType());
        //naturedesc.setText("Nature : "+meuble.getNature());
        //envdesc.setText("Environnement : "+meuble.getEnv());
        Picasso.with(this).load(meuble.getImageurl()).into(pic);

        Button left_button = findViewById(R.id.buttondesc);
        Button right_button = findViewById(R.id.buttoninfo);

        left_button.setText("Desc.");
        right_button.setText("Infos");

        getSupportFragmentManager().beginTransaction().add(R.id.fragment,new FirstFragment()).commit();

    }

    public void button_desc(View view) {
        if(button_state==1){
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment,new FirstFragment())
                    .commit();
            button_state=0;
        }
    }

    public void button_info(View view) {
        if(button_state==0){
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment,new SecondFragment())
                    .commit();
            button_state=1;
        }
    }
}
