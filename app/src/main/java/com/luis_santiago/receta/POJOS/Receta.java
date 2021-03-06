package com.luis_santiago.receta.POJOS;

import android.content.ContentValues;

import com.luis_santiago.receta.*;

/**
 * Created by Luis Santiago on 7/2/18.
 */
public class Receta {
    private String id;
    private String nombre;
    private int personas;
    private String descripcion;
    private String preparacion;
    private String image;
    private int fav;

    public Receta(){

    }

    public Receta(String id, String nombre, int personas, String descripcion, String preparacion,
                  String image, int fav) {
        this.id = id;
        this.nombre = nombre;
        this.personas = personas;
        this.descripcion = descripcion;
        this.preparacion = preparacion;
        this.image = image;
        this.fav = fav;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getPersonas() {
        return personas;
    }

    public void setPersonas(int personas) {
        this.personas = personas;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getPreparacion() {
        return preparacion;
    }

    public void setPreparacion(String preparacion) {
        this.preparacion = preparacion;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getFav() {
        return fav;
    }

    public void setFav(int fav) {
        this.fav = fav;
    }

    public ContentValues toValues(){
        ContentValues contentValues = new ContentValues(7);
        contentValues.put(SQLConstants.COLUMN_ID, id);
        contentValues.put(SQLConstants.COLUMN_NAME , nombre);
        contentValues.put(SQLConstants.COLUMN_PEOPLE , personas);
        contentValues.put(SQLConstants.COLUMN_DESCRIPTION, descripcion);
        contentValues.put(SQLConstants.COLUMN_IMAGE , image);
        contentValues.put(SQLConstants.COLUMN_FAVORITES , fav);
        return contentValues;
    }
}
