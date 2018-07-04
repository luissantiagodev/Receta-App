package com.luis_santiago.receta;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;

import com.luis_santiago.receta.POJOS.Receta;
import com.luis_santiago.receta.adapter.RecetaAdapter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerRecetas;
    private ArrayList<Receta> mRecetaList;
    private RecetaAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecetaList = new ArrayList<>();
        setRecyclerView();
    }

    public void setRecyclerView(){
        recyclerRecetas = findViewById(R.id.recycleview);
        mAdapter = new RecetaAdapter(mRecetaList , this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerRecetas.setLayoutManager(linearLayoutManager);
        recyclerRecetas.setAdapter(mAdapter);
    }
}
