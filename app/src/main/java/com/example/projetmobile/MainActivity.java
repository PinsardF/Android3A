package com.example.projetmobile;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.gson.Gson;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    RecyclerView.Adapter mAdapter;
    RecyclerView.LayoutManager layoutManager;
    private Controller controller;
    private static List<Meuble> meubleList;
    private static final String mot = "AH";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

        controller = new Controller(this);
        controller.start();
    }

    public void showList(List<Meuble> input) {
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        mAdapter = new MyAdapter(input, getListener());
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
}
