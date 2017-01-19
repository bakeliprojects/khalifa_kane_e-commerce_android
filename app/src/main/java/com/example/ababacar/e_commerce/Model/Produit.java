package com.example.ababacar.e_commerce.Model;

import io.realm.RealmObject;

/**
 * Created by Ababacar on 16/01/2017.
 */

public class Produit extends RealmObject {
    int id;
    String marque;
    String categorie;
    String annee;
    double prix;

    @Override
    public String toString() {
        return "Produit{" +
                "id=" + id +
                ", marque='" + marque + '\'' +
                ", categorie='" + categorie + '\'' +
                ", annee=" + annee +
                ", prix=" + prix +
                ", description='" + description + '\'' +
                ", photo='" + photo + '\'' +
                ", created_at='" + created_at + '\'' +
                '}';
    }

    String description;
    String photo;
    String created_at;

    public Produit(){}

    public Produit(Produit p)
    {
        this.marque=p.marque;
        this.categorie=p.categorie;
        this.prix=p.prix;
        this.annee=p.annee;
        this.description=p.description;
        this.photo=p.photo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMarque() {
        return marque;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public String getAnnee() {
        return annee;
    }

    public void setAnnee(String annee) {
        this.annee = annee;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }
}
