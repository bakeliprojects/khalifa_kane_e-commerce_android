package com.example.ababacar.e_commerce.Service;

import com.example.ababacar.e_commerce.Model.Produit;

import java.util.List;



import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Ababacar on 20/01/2017.
 */

public interface Myservice {

    //public static final String ENDPOINT = "http://localhost/APIREST/public/";

   /* @GET("/categorie")
    List SearchByCategorie(@Query("categorie") String query);

    @GET("/recherche")
    List SearchByMarque(@Field("marque") String query);

    @GET("/admin/commande")
    List ListCommande();*/

    @GET("/produit")
    Call<List<Produit>> ListProduit();

    /*@GET("/produit/{id}/edit")
    Produit EditProduit(@Path("id") int id);*/

}
