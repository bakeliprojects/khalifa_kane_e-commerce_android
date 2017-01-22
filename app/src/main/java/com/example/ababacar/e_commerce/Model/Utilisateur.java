package com.example.ababacar.e_commerce.Model;

import io.realm.RealmObject;

/**
 * Created by Ababacar on 20/01/2017.
 */

public class Utilisateur extends RealmObject {

    int id;
    String nom;
    String prenom;
    String login;
    String mpass;
    String statut;

    @Override
    public String toString() {
        return "Utilisateur{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", login='" + login + '\'' +
                ", mpass='" + mpass + '\'' +
                ", statut='" + statut + '\'' +
                '}';
    }



    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getMpass() {
        return mpass;
    }

    public void setMpass(String mpass) {
        this.mpass = mpass;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }



}
