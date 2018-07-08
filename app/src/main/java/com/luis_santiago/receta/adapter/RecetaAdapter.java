package com.luis_santiago.receta.adapter;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.content.FileProvider;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.luis_santiago.receta.POJOS.Receta;
import com.luis_santiago.receta.R;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;

/**
 * Created by Luis Santiago on 7/2/18.
 */
public class RecetaAdapter extends RecyclerView.Adapter<RecetaAdapter.RecetaHolder>{

    private ArrayList<Receta> mRecetaList;
    private Context mContext;

    public RecetaAdapter(ArrayList<Receta> recetas , Context context){
        this.mRecetaList = recetas;
        this.mContext = context;
    }

    @NonNull
    @Override
    public RecetaHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_receta , parent , false);
        return new RecetaHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecetaHolder holder, int position) {
        Receta currentReceta = mRecetaList.get(position);

        holder.bindItem(currentReceta);
    }

    @Override
    public int getItemCount() {
        return mRecetaList.size();
    }

    class RecetaHolder extends RecyclerView.ViewHolder{
        private CardView mCardView;
        private ImageView mImageView;
        private TextView name, people, description;

        RecetaHolder(View v) {
            super(v);
            mCardView = v.findViewById(R.id.card_view_receta);
            mImageView = v.findViewById(R.id.image_view_receta);
            name = v.findViewById(R.id.name);
            people = v.findViewById(R.id.quantity_persons);
            description = v.findViewById(R.id.description);
        }

        void bindItem(Receta currentReceta) {
            name.setText(currentReceta.getNombre());
            people.setText("Personas " + String.valueOf(currentReceta.getPersonas()));
            description.setText(currentReceta.getDescripcion());
            Log.e("ADAPTER" , currentReceta.getImage());

            String pathName = Environment.getExternalStorageDirectory().getPath() + currentReceta.getImage();
            File path = new File(pathName);
            if(path.exists()){
                BitmapFactory.Options options = new BitmapFactory.Options();
                Bitmap bm = BitmapFactory.decodeFile(pathName, options);
                mImageView.setImageBitmap(bm);
            }
            else{
                //Set default picture or do nothing.
                Log.e("ADAPTER" , "dOES NOT LOAD");
            }

        }
    }

}
