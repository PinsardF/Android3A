package com.example.projetmobile.view;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.projetmobile.model.Meuble;
import com.example.projetmobile.R;
import com.example.projetmobile.controller.Controller;
import com.google.gson.Gson;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String PREFS = "PREFS";
    RecyclerView recyclerView;
    RecyclerView.Adapter mAdapter;
    RecyclerView.LayoutManager layoutManager;
    private Controller controller;
    private static List<Meuble> meubleList;
    private static final String mot = "AH";
    //Test

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

        Button button = findViewById(R.id.refreshbutton);
        button.setBackgroundColor(Color.parseColor("#20b2aa"));

        controller = new Controller(this, getBaseContext().getSharedPreferences(PREFS,MODE_PRIVATE));
        controller.start();
    }

    public void showList(List<Meuble> input) {
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        mAdapter = new MyAdapter(input, getListener(), this);
        recyclerView.setAdapter(mAdapter);
    }

    private MyAdapter.OnItemClickListener getListener() {
        return new MyAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Meuble meuble) {
                Intent intent = new Intent(getApplicationContext(), SecondActivity.class);
                Gson gson = new Gson();
                String json = gson.toJson(meuble);
                intent.putExtra("meubleData", json);
                startActivity(intent);
            }
        };
    }

    public void refresh(View view) {
        controller.reload();
        Toast refreshToast = Toast.makeText(this,"Successfully refreshed !",Toast.LENGTH_SHORT);
        refreshToast.show();
    }
}
