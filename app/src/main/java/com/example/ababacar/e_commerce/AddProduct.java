package com.example.ababacar.e_commerce;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.ababacar.e_commerce.Model.Produit;

import io.realm.Realm;


public class AddProduct extends AppCompatActivity {

    private Realm realm;

    private Button enregistrer = null;
    private  Button effacer = null;
    private Button addphoto=null;
    private EditText marque= null;
    private EditText categoris= null;
    private EditText prix= null;
    private EditText annee= null;
    private EditText description= null;
    private String foto ="chemin";
    int RESULT_LOAD_IMAGE=1;
    String picturePath;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);
        realm = Realm.getDefaultInstance();

        enregistrer = (Button) findViewById(R.id.enregistrer) ;
        effacer = (Button) findViewById(R.id.effacer) ;
        addphoto = (Button) findViewById(R.id.photo);

        marque = (EditText) findViewById(R.id.marque);
        categoris = (EditText) findViewById(R.id.categorie);
        prix = (EditText) findViewById(R.id.prix);
        annee = (EditText) findViewById(R.id.annee);
        description = (EditText) findViewById(R.id.description);




        enregistrer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Sate_to_Database(marque.getText().toString().trim(),categoris.getText().toString().trim(),prix.getText().toString().trim(),annee.getText().toString().trim(),description.getText().toString().trim(),foto);
            }
        });

        effacer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });

        addphoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(
                        Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

                startActivityForResult(i, RESULT_LOAD_IMAGE);
            }
        });
     }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        realm.close();
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

    private void Sate_to_Database(final String marque, final String categorie, final String prix, final String annee, final String description, final String foto) {

        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm bgRealm) {
                Produit produit = bgRealm.createObject(Produit.class);
                produit.setMarque(marque);
                produit.setCategorie(categorie);
                produit.setPrix(Double.parseDouble(prix));
                produit.setAnnee(annee);
                produit.setDescription(description);
                produit.setPhoto(picturePath);
            }
        }, new Realm.Transaction.OnSuccess() {
            @Override
            public void onSuccess() {
                // Transaction was a success.
                Snackbar.make(findViewById(R.id.addprod), R.string.add_product,
                        Snackbar.LENGTH_SHORT)
                        .show();
            }
        }, new Realm.Transaction.OnError() {
            @Override
            public void onError(Throwable error) {
                // Transaction failed and was automatically canceled.
                Log.e("erreur","ko");
            }
        });
    }


}
