package com.luis_santiago.receta;

import android.provider.BaseColumns;

/**
 * Created by Luis Santiago on 7/3/18.
 */

public class SQLConstants implements BaseColumns {

    //DB
    public static final String DB = "bdrecetas.db";

    //Tables
    public static final String NAME_TABLE  = "recetas";

    private String id;
    private String nombre;
    private int personas;
    private String descripcion;
    private String preparacion;
    private String image;
    private int fav;

    //Columns
    public static final String COLUMN_ID = BaseColumns._ID;
    public static final String COLUMN_NAME = "nombre";
    public static final String COLUMN_PEOPLE = "personas";
    public static final String COLUMN_DESCRIPTION = "description";
    public static final String COLUMN_IMAGE = "image";
    public static final String COLUMN_FAVORITES = "favorite";
    public static final String COLUMN_PREPARACION = "preparacion";

    //query
    public static final String SQL_RECETAS_CREATE_TABLE =
            "CREATE TABLE " + NAME_TABLE + "(" +
                    COLUMN_ID +" TEXT PRIMARY KEY, " +
                    COLUMN_NAME + " TEXT , "+
                    COLUMN_PEOPLE + " INT , " +
                    COLUMN_DESCRIPTION + " TEXT , "+
                    COLUMN_PREPARACION + " TEXT , "+
                    COLUMN_IMAGE + " TEXT , "+
                    COLUMN_FAVORITES + " INT "+ ")";

    public static final String [] ALL_COLUMNS = {
            COLUMN_ID,
            COLUMN_NAME,
            COLUMN_PEOPLE,
            COLUMN_DESCRIPTION,
            COLUMN_PREPARACION,
            COLUMN_IMAGE,
            COLUMN_FAVORITES
    };


    public static final String SQL_DELETE_TABLE = "DROP TABLE " + NAME_TABLE;

}