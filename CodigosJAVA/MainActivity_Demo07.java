package com.example.a2026_01_holamundoandroid;

import android.os.Bundle;
import android.widget.TabHost;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;


import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.List;


public class MainActivity_Demo07 extends AppCompatActivity {

    Spinner SP1, SP2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_demo07);

        //setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Array of Months acting as a data pump
        String[] objects = { "January", "Feburary", "March", "April", "May",
                "June", "July", "August", "September", "October", "November","December" };

        // Por defecto utiliza una configuración predeterminada
        ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1 ,objects);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Es posibile substituir por colores chirriantes que el usuario desee
        //ArrayAdapter adapter = new ArrayAdapter(this,R.layout.fila_spinner ,objects);
        //adapter.setDropDownViewResource(R.layout.fila_spinner_despega);


        SP1=(Spinner) findViewById(R.id.spinner);
        SP2=(Spinner) findViewById(R.id.spinner2);


        SP1.setAdapter(adapter);
        SP2.setAdapter(adapter);

        SP1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(), "Spinner 1" + parent.getItemAtPosition(position).toString() + "Elemento:[" + position + "]", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        SP2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(),"Spinner 2"+parent.getItemAtPosition(position).toString()+"Elemento:["+position+"]",Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });





    }
}