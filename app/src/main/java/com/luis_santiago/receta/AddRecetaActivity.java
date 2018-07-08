package com.luis_santiago.receta;

import android.Manifest;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.Toast;

import com.luis_santiago.receta.POJOS.Receta;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AddRecetaActivity extends AppCompatActivity {
    private static final String TAG = AddRecetaActivity.class.getSimpleName();
    private Button addButton , takePhoto;
    private TextInputEditText id,name,personas,description,preparacion;
    private RatingBar ratingBar;
    private Data data;
    private Boolean isPhototaken = false;
    private static final int TAKE_PICTURE = 1;
    private final int REQUEST_CODE_PERMISSION = 2;
    private Uri imageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_receta);
        init();
        data = new Data(this);
        addButton.setOnClickListener(View->{
            //Add
            if(isPhototaken){
                data.open();
                Receta receta = new Receta(
                        id.getText().toString().trim(),
                        name.getText().toString().trim(),
                        Integer.valueOf(personas.getText().toString().trim()),
                        description.getText().toString().trim(),
                        preparacion.getText().toString().trim(),
                        imageUri.toString(),
                        ratingBar.getNumStars()
                );
                data.insertReceta(receta);
                data.close();
                Toast.makeText(getApplicationContext() , "Registro agregado" , Toast.LENGTH_LONG).show();
                finish();
            }else {
                Toast.makeText(getApplicationContext() , "Toma una foto" , Toast.LENGTH_LONG).show();
            }
        });

        takePhoto.setOnClickListener(View->{
            if(checkPermissionToStore()) {
                File pictureFile = getOutputMediaFile();
                if(pictureFile !=null){
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    intent.putExtra(MediaStore.EXTRA_OUTPUT , FileProvider.getUriForFile(this , "com.luis_santiago.receta.provider" , pictureFile));
                    intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                    imageUri = FileProvider.getUriForFile(this , "com.luis_santiago.receta.provider" , pictureFile);
                    startActivityForResult(intent , TAKE_PICTURE);
                }
            }else{
                requestPermission();
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case TAKE_PICTURE : {
                if (checkPermissionToStore()) {
                    Log.e("LOCATION IMAGE", imageUri.getPath());
                    isPhototaken = true;
//                    }
                } else {
                    requestPermission();
                }
            }
        }
    }

    private File getOutputMediaFile() {
        File mediaStorageDir = new File(Environment.getExternalStorageDirectory()
                + "/Android/data/"
                + getApplicationContext().getPackageName()
                + "/Files");

        if (! mediaStorageDir.exists()){
            if (! mediaStorageDir.mkdirs()){
                return null;
            }
        }

        String timeStamp = new SimpleDateFormat("ddMMyyyy_HHmm").format(new Date());
        File mediaFile;
        String mImageName="MI_"+ timeStamp +".jpg";
        mediaFile = new File(mediaStorageDir.getPath() + File.separator + mImageName);
        return mediaFile;
    }

    private boolean checkPermissionToStore() {
        int result = ContextCompat.checkSelfPermission(this , Manifest.permission.WRITE_EXTERNAL_STORAGE);
        return result == PackageManager.PERMISSION_GRANTED;
    }

    private void requestPermission(){
        if(ActivityCompat.shouldShowRequestPermissionRationale(this , Manifest.permission.WRITE_EXTERNAL_STORAGE)){
            Toast.makeText(this, "Write External Storage permission allows us to do store images. Please allow this permission in App Settings.", Toast.LENGTH_LONG).show();
        }else{
            ActivityCompat.requestPermissions(this , new String[] {Manifest.permission.WRITE_EXTERNAL_STORAGE} , REQUEST_CODE_PERMISSION);
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case REQUEST_CODE_PERMISSION:{
                if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    Log.e("value", "Permission Granted, Now you can use local drive .");
                }else{
                    Log.e("value", "Permission Denied, You cannot use local drive .");
                }
                break;
            }
        }
    }

    private void init() {
        addButton = findViewById(R.id.add);
        id = findViewById(R.id.id);
        name = findViewById(R.id.nombre);
        personas = findViewById(R.id.personas);
        description = findViewById(R.id.description);
        preparacion = findViewById(R.id.preparacion);
        ratingBar = findViewById(R.id.favoritos);
        takePhoto = findViewById(R.id.photo);
    }
}
