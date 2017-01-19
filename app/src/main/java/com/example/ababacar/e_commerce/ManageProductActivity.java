package com.example.ababacar.e_commerce;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.example.ababacar.e_commerce.Model.Produit;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by Ababacar on 18/01/2017.
 */

public class ManageProductActivity extends AppCompatActivity {

    private SwipeRefreshLayout swipeContainer;
    private Realm realm;
    private Button btnAdd;
    private Button btnEdit;
    private Button btnDel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manage_product);
        realm = Realm.getDefaultInstance();
        initViews();

        btnAdd = (Button) findViewById(R.id.btnadd) ;
        btnEdit = (Button) findViewById(R.id.btneditproduit) ;
        btnDel = (Button) findViewById(R.id.btndelproduit) ;

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ManageProductActivity.this, AddProduct.class);
                startActivity(intent);
            }
        });



    }

    private void initViews() {
        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.card_recycler_view_manage_product);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);

        ArrayList produit = prepareData();
        GaleryAdapterManager adapter = new GaleryAdapterManager(produit,getApplicationContext());
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
