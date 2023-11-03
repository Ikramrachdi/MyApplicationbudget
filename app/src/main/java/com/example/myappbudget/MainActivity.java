package com.example.myappbudget;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private EditText editTextdescription, editTextmontant;
    private Spinner spinner;
    private String[] categories = {"Alimentation", "Loisirs", "Transport", "Autre"};
    private ListView listViewExpens;
    private ArrayList<String> expensList;
    private ArrayAdapter<String> adapter;
    private Button btnajouter, btnafficher;
    private int expenseCount = 0;
    private DatePicker datePicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextdescription = findViewById(R.id.description);
        spinner = findViewById(R.id.spinnercat);
        editTextmontant = findViewById(R.id.montant);
        datePicker = findViewById(R.id.date);
        btnafficher = findViewById(R.id.btnafficher);
        btnajouter = findViewById(R.id.btnajouter);
        listViewExpens = findViewById(R.id.listViewdepenses);

        editTextdescription.setTextColor(ContextCompat.getColor(this, android.R.color.black));
        editTextmontant.setTextColor(ContextCompat.getColor(this, android.R.color.black));

        btnajouter.setTextColor(ContextCompat.getColor(this, android.R.color.holo_blue_dark));
        btnafficher.setTextColor(ContextCompat.getColor(this, android.R.color.holo_blue_dark));

        expensList = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, expensList);
        listViewExpens.setAdapter(adapter);

        ArrayAdapter<String> categorieAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, categories);
        categorieAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(categorieAdapter);

        btnajouter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                expenseCount++;
                String description = editTextdescription.getText().toString();
                String categorie = spinner.getSelectedItem().toString();
                String montant = editTextmontant.getText().toString();
                String date = datePicker.getDayOfMonth() + "/" + (datePicker.getMonth() + 1) + "/" + datePicker.getYear();
                String expense = "Dépense #" + expenseCount + ": " + description + "\nCatégorie: " + categorie + "\nMontant: $" + montant + "\nDate: " + date;
                expensList.add(expense);
                adapter.notifyDataSetChanged();
                Toast.makeText(MainActivity.this, "Dépense ajoutée", Toast.LENGTH_SHORT).show();
            }
        });

        btnafficher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MainActivitydepense.class);
                intent.putStringArrayListExtra("depenses", expensList);
                startActivity(intent);
            }
        });
    }
}
