package com.example.myappbudget;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Toast;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class MainActivitydepense extends AppCompatActivity {
    private ListView listViewdepenses;
    private EditText totaldepenses;
    private Button btnSerialiser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activitydepense);

        listViewdepenses = findViewById(R.id.listViewdepenses);
        totaldepenses = findViewById(R.id.totaldepenses);
        btnSerialiser = findViewById(R.id.btnSerialiser);

        Intent intent = getIntent();
        String[] depenses = intent.getStringArrayExtra("depenses");

        if (depenses != null && depenses.length > 0) {
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, depenses);
            listViewdepenses.setAdapter(adapter);

            // Calcul du total des dépenses
            int total = 0;
            for (String expense : depenses) {
                try {
                    int amount = Integer.parseInt(expense.split(":")[2].replace("$", ""));
                    total += amount;
                } catch (NumberFormatException e) {
                    // Gérer l'exception en cas de format incorrect des montants
                    e.printStackTrace();
                }
            }

            // Affichage du total des dépenses
            totaldepenses.setText("Total : $" + total);
        } else {
            // Gérer le cas où l'array depenses est vide ou null
           Toast.makeText(this, "Aucune dépense disponible.", Toast.LENGTH_SHORT).show();
        }

        btnSerialiser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

              Depense.Serialiser(getApplicationContext());
            }
        });
    }}










