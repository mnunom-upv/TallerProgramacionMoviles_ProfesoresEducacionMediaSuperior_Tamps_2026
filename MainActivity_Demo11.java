package com.example.a2026_01_holamundoandroid;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class MainActivity_Demo11 extends AppCompatActivity {

    Button B1, B2;
    Context CX;
    ArrayList<String> ListaConceptos; // = new ArrayList<User>();
    ArrayAdapter<String> AdapterListaConceptos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_demo11);

        ListaConceptos= new ArrayList<String>();
        ListaConceptos.add("Primero");
        ListaConceptos.add("Segundo");
        ListaConceptos.add("Tercero");
        CX = this;

        B1 = (Button) findViewById (R.id.button1);
        B2 = (Button) findViewById (R.id.button2);

        B1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(CX);
                builder.setMessage("Selecciona una opción");
                builder.setCancelable(true);
                // Add the buttons
                builder.setPositiveButton("Matutino", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User clicked OK button
                        Toast.makeText(CX,"Seleccionaste Excelente",Toast.LENGTH_SHORT).show();
                        dialog.cancel();
                    }
                });
                builder.setNegativeButton("Vespertino", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User cancelled the dialog
                        Toast.makeText(CX,"Seleccionaste ser Mediocre",Toast.LENGTH_SHORT).show();
                        dialog.cancel();
                    }
                });
                // Set other dialog properties

                // Create the AlertDialog
                AlertDialog dialog = builder.create();
                // Mostrar el dialogl
                dialog.show();


            }
        });

        B2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                TextView text1, text2;
                EditText ETD1;
                AutoCompleteTextView ETD2;
                // custom dialog
                final Dialog dialog = new Dialog(CX);
                dialog.setContentView(R.layout.custom_dialog);
                dialog.setTitle("Modificar/eliminar artículo");

                // set the custom dialog components - text, image and button
                text1 = (TextView) dialog.findViewById(R.id.textDialog1);
                text1.setText("Costo");

                text2 = (TextView) dialog.findViewById(R.id.textDialog2);
                text2.setText("Descripción");

                ETD1 = (EditText) dialog.findViewById(R.id.ETDialog1);
                ETD1.setText(""+20);
                ETD2 = (AutoCompleteTextView) dialog.findViewById(R.id.ETDialog2);
                ETD2.setText("");

                AdapterListaConceptos = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.select_dialog_item, ListaConceptos);
                ETD2.setThreshold(1);
                ETD2.setAdapter(AdapterListaConceptos);


                Button dialogButton1 = (Button) dialog.findViewById(R.id.dialogButtonOK);
                // if button is clicked, close the custom dialog
                dialogButton1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //String Cadena=ETD1.getText().toString();
                        //Toast.makeText(getApplicationContext(),"COSTO NUEVO:"+Cadena,Toast.LENGTH_SHORT).show();


                        dialog.dismiss();
                    }
                });

                Button dialogButton2 = (Button) dialog.findViewById(R.id.dialogButtonBYE);
                // if button is clicked, close the custom dialog
                dialogButton2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {


                        dialog.dismiss();
                    }
                });
                dialog.show();

            }
        });
    }
}
