package com.luis_santiago.receta;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.luis_santiago.receta.POJOS.Receta;
import com.luis_santiago.receta.adapter.RecetaAdapter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerRecetas;
    private ArrayList<Receta> mRecetaList;
    private RecetaAdapter mAdapter;
    private FloatingActionButton mFab;
    private Data data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        data = new Data(this);
        mRecetaList = new ArrayList<>();
        setRecyclerView();
        mFab = findViewById(R.id.fab);
        mFab.setOnClickListener(View->{
            startActivity(new Intent(this , AddRecetaActivity.class));
        });



    }

    public void setRecyclerView(){
        recyclerRecetas = findViewById(R.id.recycleview);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerRecetas.setLayoutManager(linearLayoutManager);
        update();
    }


    public ArrayList<Receta> getData(){
        return data.getAllRecetas();
    }

    public void update(){
        mAdapter = new RecetaAdapter(getData(), this);
        recyclerRecetas.setAdapter(mAdapter);
    }
}
