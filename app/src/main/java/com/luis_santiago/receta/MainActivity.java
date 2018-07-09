package com.luis_santiago.receta;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
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

        update();
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();
                RecetaAdapter recetaAdapter = (RecetaAdapter) recyclerRecetas.getAdapter();
                String value = recetaAdapter.mRecetaList.get(position).getId();
                data.deleteItem(value);
                update();
            }
        });

        itemTouchHelper.attachToRecyclerView(recyclerRecetas);
    }

    public void setRecyclerView(){
        recyclerRecetas = findViewById(R.id.recycleview);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerRecetas.setLayoutManager(linearLayoutManager);
    }


    @Override
    protected void onResume() {
        super.onResume();
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
