package com.luis_santiago.receta.helpers;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Luis Santiago on 7/3/18.
 */
public class DBHelper  extends SQLiteOpenHelper{

    public static final int DB_VERSION = 1;

    public DBHelper(Context context){
        super(context, SQLConstants.DB, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.e("DB" , SQLConstants.SQL_RECETAS_CREATE_TABLE);
        db.execSQL(SQLConstants.SQL_RECETAS_CREATE_TABLE);
    }

    @Override 
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQLConstants.SQL_DELETE_TABLE);
        onCreate(db);
    }
}
