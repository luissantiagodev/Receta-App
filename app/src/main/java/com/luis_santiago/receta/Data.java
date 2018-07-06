package com.luis_santiago.receta;

import android.content.ContentValues;
import android.content.Context;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.luis_santiago.receta.POJOS.Receta;
import com.luis_santiago.receta.helpers.DBHelper;

import java.util.ArrayList;

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

}
