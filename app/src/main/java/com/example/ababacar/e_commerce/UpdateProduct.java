package com.example.ababacar.e_commerce;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.annotation.StringDef;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.ababacar.e_commerce.Model.Produit;

import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmResults;



/**
 * Created by Ababacar on 19/01/2017.
 */

public class UpdateProduct  extends AppCompatActivity {

    private static final String TAG = "UpdateProduct";

    private Realm realm;

    private Button modification = null;
    private Button effacer = null;
    private Button addphoto = null;
    private EditText marque = null;
    private EditText categoris = null;
    private EditText prix = null;
    private EditText annee = null;
    private EditText description = null;
    private  ImageView imageview;
    int RESULT_LOAD_IMAGE = 1;
    String picturePath;
    Produit pp;

    String idproduit;


    //Produit pp = realm.where(Produit.class).lessThan("id", Integer.parseInt(idproduit)).findFirst();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_product);
        realm = Realm.getDefaultInstance();

        Bundle extra = getIntent().getExtras();
        int idproduit = extra.getInt("extra");
        Log.e(TAG, "onCreate: "+idproduit);
        pp = realm.where(Produit.class).equalTo("id", idproduit).findFirst();
        picturePath=pp.getPhoto();


        modification = (Button) findViewById(R.id.modification);
        effacer = (Button) findViewById(R.id.effacer);
        addphoto = (Button) findViewById(R.id.photo);
        marque = (EditText) findViewById(R.id.marque);
        categoris = (EditText) findViewById(R.id.categorie);
        prix = (EditText) findViewById(R.id.prix);
        annee = (EditText) findViewById(R.id.annee);
        description = (EditText) findViewById(R.id.description);
        imageview = (ImageView) findViewById(R.id.imgView);

        remplirchamps(pp);

        modification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //final RealmResults<Produit> p = realm.where(Produit.class).lessThan("id", pp.getId()).findAll();

                realm.executeTransaction(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {
                        Produit prod = realm.where(Produit.class).equalTo("id", pp.getId()).findFirst();
                        prod.setMarque(marque.getText().toString().trim());
                        prod.setCategorie(categoris.getText().toString().trim());
                        prod.setAnnee(annee.getText().toString().trim());
                        prod.setPrix(Double.parseDouble(prix.getText().toString().trim()));
                        prod.setDescription(description.getText().toString().trim());
                        prod.setPhoto(picturePath);
                    }
                });

            }
        });

        effacer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {
            Uri selectedImage = data.getData();
            String[] filePathColumn = {MediaStore.Images.Media.DATA};

            Cursor cursor = getContentResolver().query(selectedImage,
                    filePathColumn, null, null, null);
            cursor.moveToFirst();

            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            picturePath = cursor.getString(columnIndex);
            cursor.close();
            ImageView imageView = (ImageView) findViewById(R.id.imgView);
            imageView.setImageBitmap(BitmapFactory.decodeFile(picturePath));
        }
    }

    public void remplirchamps(Produit pp) {
        marque.setText(pp.getMarque());
        categoris.setText(pp.getCategorie());
        annee.setText(pp.getAnnee());
        prix.setText(String.valueOf(pp.getPrix()));
        description.setText(pp.getDescription());
        imageview.setImageBitmap(BitmapFactory.decodeFile(pp.getPhoto()));
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        realm.close();
    }
}