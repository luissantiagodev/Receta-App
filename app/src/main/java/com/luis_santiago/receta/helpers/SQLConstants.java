package com.luis_santiago.receta.helpers;

import android.provider.BaseColumns;

/**
 * Created by Luis Santiago on 7/3/18.
 */

class SQLConstants implements BaseColumns {

    //DB
    static final String DB = "bdrecetas.db";

    //Tables
    static final String NAME_TABLE  = "recetas";

    private String id;
    private String nombre;
    private int personas;
    private String descripcion;
    private String preparacion;
    private String image;
    private int fav;

    //Columns
    static final String COLUMN_ID = BaseColumns._ID;
    static final String COLUMN_NAME = "nombre";
    static final String COLUMN_PEOPLE = "personas";
    static final String COLUMN_DESCRIPTION = "description";
    static final String COLUMN_IMAGE = "image";
    static final String COLUMN_FAVORITES = "favorite";
    static final String COLUMN_PREPARACION = "preparacion";

    //query
    static final String SQL_RECETAS_CREATE_TABLE =
            "CREATE TABLE " + NAME_TABLE + "(" +
                    COLUMN_ID +" TEXT PRIMARY KEY, " +
                    COLUMN_NAME + " TEXT , "+
                    COLUMN_PEOPLE + "INT , " +
                    COLUMN_DESCRIPTION + " TEXT , "+
                    COLUMN_PREPARACION + " TEXT , "+
                    COLUMN_IMAGE + " TEXT , "+
                    COLUMN_FAVORITES + " INT  , "+ ")";


    static final String SQL_DELETE_TABLE = "DROP TABLE " + NAME_TABLE;

}