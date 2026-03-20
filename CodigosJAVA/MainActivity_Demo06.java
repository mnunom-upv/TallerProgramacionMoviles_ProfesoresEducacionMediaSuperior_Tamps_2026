package com.example.a2026_01_holamundoandroid;

import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

public class MainActivity_Demo06 extends AppCompatActivity {
    ListView LV1;
    Button B1,  B2;
    TextView TV2;

    static final String[] Frutas = new String[] { "Manzana", "Mango", "Durazno", "Platano", "Fresa", "Uva", "Sandia","Melon","Ranbután","Zapote","Maracuya"};
    ArrayAdapter<String> adapter;
    List<String> lista;
    String temp;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main_demo06);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });



        lista = new ArrayList<String>();
        lista.add("Programacion");
        lista.add("Matematicas");
        lista.add("Calculo");
        lista.add("Algebra");
        lista.add("Ing. Software");

        LV1 = findViewById(R.id.listView);

        B1 = findViewById(R.id.button);
        B2 = findViewById(R.id.button2);
        TV2 = findViewById(R.id.textView2);

        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, lista);

        //LV1.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        //adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_multiple_choice,lista);


        LV1.setAdapter(adapter);
        LV1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(),
                        ((TextView) view).getText()+"- Posicion: "+position, Toast.LENGTH_SHORT).show();
            }
        });

        B1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lista.add("Materia Patito");
                adapter.notifyDataSetChanged();
            }
        });

        B2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //getSelectedItems();
            }
        });


    }
/*
    private void getSelectedItems(){
        temp="Selected Elements: \n";
        SparseBooleanArray checked = LV1.getCheckedItemPositions();
        for (int i = 0; i < LV1.getAdapter().getCount(); i++) {
            if (checked.get(i)) { // Do something
                temp+=adapter.getItem(i).toString()+" ["+i+"]"+"\n";
            }
        }
        TV2.setText(temp);
    } */

}