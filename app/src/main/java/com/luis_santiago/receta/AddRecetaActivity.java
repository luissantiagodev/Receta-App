package com.luis_santiago.receta;

import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import com.luis_santiago.receta.POJOS.Receta;

public class AddRecetaActivity extends AppCompatActivity {
    private Button addButton;
    private TextInputEditText id,name,personas,description,preparacion;
    private RatingBar ratingBar;
    private Data data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_receta);
        init();
        data = new Data(this);
        addButton.setOnClickListener(View->{
            //Add
            data.open();
            Receta receta = new Receta(
                    id.getText().toString().trim(),
                    name.getText().toString().trim(),
                    Integer.valueOf(personas.getText().toString().trim()),
                    description.getText().toString().trim(),
                    preparacion.getText().toString().trim(),
                    "image.png",
                    ratingBar.getNumStars()
            );
            data.insertReceta(receta);
            data.close();
            Toast.makeText(getApplicationContext() , "Registro agregado" , Toast.LENGTH_LONG).show();
            finish();
        });
    }

    private void init() {
        addButton = findViewById(R.id.add);
        id = findViewById(R.id.id);
        name = findViewById(R.id.nombre);
        personas = findViewById(R.id.personas);
        description = findViewById(R.id.description);
        preparacion = findViewById(R.id.preparacion);
        ratingBar = findViewById(R.id.favoritos);
    }
}
