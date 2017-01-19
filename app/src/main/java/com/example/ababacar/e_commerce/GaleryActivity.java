package com.example.ababacar.e_commerce;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.ababacar.e_commerce.Model.Produit;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by Ababacar on 18/01/2017.
 */

public class GaleryActivity extends AppCompatActivity {


    private Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        realm = Realm.getDefaultInstance();
        setContentView(R.layout.galery_activity_main);

        initViews();
    }

    private void initViews() {
        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.card_recycler_view);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);

        ArrayList produit = prepareData();
        GaleryAdapter adapter = new GaleryAdapter(getApplicationContext(),produit);
        recyclerView.setAdapter(adapter);

    }

    private ArrayList prepareData() {
        ArrayList Lproduit = new ArrayList<>();
        RealmResults<Produit> result = realm.where(Produit.class).findAllAsync();
        result.load();
        for (Produit produit:result){
            Lproduit.add(new Produit(produit));
        }
        return Lproduit;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        realm.close();
    }
}
