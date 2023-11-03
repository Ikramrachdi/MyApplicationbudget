package com.example.myappbudget;

import android.content.Context;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class Depense implements Serializable {
    private String nom;
    private String categorie;
    private int montant;
    private Date dateDebut;
    private Date dateFin;
    private static ArrayList<Depense> depenseArrayList = new ArrayList<>();

    public Depense() {
    }
    public Depense(String nom, String categorie, int montant, Date dateDebut, Date dateFin) {
        this.nom = nom;
        this.categorie = categorie;
        this.montant = montant;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
    }
    // Getters et setters

    public static ArrayList<Depense> getDepenseArrayList() {
        return depenseArrayList;
    }

    public static void addDepense(Depense depense) {
        depenseArrayList.add(depense);
    }

    public static void Serialiser (Context context) {
        try {
            File path = context.getFilesDir();
            File file = new File(path, "depenses.ser");
            FileOutputStream fos = new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(depenseArrayList);
            oos.close();
            fos.close();
        } catch (IOException e) {
            Log.e("Depense", "Erreur lors de la sérialisation : " + e.getMessage());
        }
    }
}





