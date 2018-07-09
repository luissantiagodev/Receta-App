package com.luis_santiago.receta;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.luis_santiago.receta.POJOS.Receta;
import com.luis_santiago.receta.helpers.DBHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Luis Santiago on 7/4/18.
 */
public class Data {
    Context mContext;
    SQLiteDatabase mSqliteDatabase;
    SQLiteOpenHelper mSqliteOpenHelper;

    public Data(Context context){
        this.mContext = context;
        mSqliteOpenHelper = new DBHelper(context);
        mSqliteDatabase = mSqliteOpenHelper.getWritableDatabase();
    }

    public void open(){
        mSqliteDatabase = mSqliteOpenHelper.getWritableDatabase();
    }

    public void close(){
        mSqliteOpenHelper.close();
    }

    public void insertReceta(Receta receta){
        ContentValues contentValues = receta.toValues();
        mSqliteDatabase.insert(SQLConstants.NAME_TABLE , null , contentValues);
    }


    public Long getItemsCount(){
        return DatabaseUtils.queryNumEntries(mSqliteDatabase , SQLConstants.NAME_TABLE);
    }

    public void insertRecetas(ArrayList<Receta> recetas){
        long items = getItemsCount();
        if(items == 0){
            for(Receta receta : recetas){
                insertReceta(receta);
            }
        }
    }

    public ArrayList<Receta> getAllRecetas(){
        ArrayList<Receta> recetas = new ArrayList<>();
        Cursor cursor = mSqliteDatabase.query(
                SQLConstants.NAME_TABLE,
                SQLConstants.ALL_COLUMNS,
                null,
                null,
                null,
                null,
                null
        );
        while(cursor.moveToNext()){
            Receta receta = new Receta();
            receta.setId(cursor.getString(cursor.getColumnIndex(SQLConstants.COLUMN_ID)));
            receta.setNombre(cursor.getString(cursor.getColumnIndex(SQLConstants.COLUMN_NAME)));
            receta.setPersonas(cursor.getInt(cursor.getColumnIndex(SQLConstants.COLUMN_PEOPLE)));
            receta.setDescripcion(cursor.getString(cursor.getColumnIndex(SQLConstants.COLUMN_DESCRIPTION)));
            receta.setPreparacion(cursor.getString(cursor.getColumnIndex(SQLConstants.COLUMN_PREPARACION)));
            receta.setFav(cursor.getInt(cursor.getColumnIndex(SQLConstants.COLUMN_FAVORITES)));
            receta.setImage(cursor.getString(cursor.getColumnIndex(SQLConstants.COLUMN_IMAGE)));
            recetas.add(receta);
        }

        return recetas;
    }


    public ArrayList<Receta> getFavs (){
        ArrayList<Receta> recetas = new ArrayList<>();
        String [] whereArgs = new String[]{String.valueOf(1)};
        return recetas;
    }

    public void deleteItem(String id){
        String [] whereArfs = new String[] {String.valueOf(id)};
        mSqliteDatabase.delete(SQLConstants.NAME_TABLE , SQLConstants.WHERE_CLAUSE_ID , whereArfs);
    }

}
