package com.example.a2026_01_holamundoandroid;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity_Demo03 extends AppCompatActivity {

    Button B1;
    EditText ET1;

    View.OnClickListener listener = v -> {
        String tag = (String) v.getTag();
        Toast.makeText(this, "Clicked: " + tag, Toast.LENGTH_SHORT).show();
    };

    Button B11, B12, B13, B21, B22, B23, B31, B32, B33;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main_demo03);

        InicializarVistas ();
        InicializarTags ();

        B11.setOnClickListener(listener);
        B12.setOnClickListener(listener);
        B13.setOnClickListener(listener);
        B21.setOnClickListener(listener);
        B22.setOnClickListener(listener);
        B23.setOnClickListener(listener);
        B31.setOnClickListener(listener);
        B32.setOnClickListener(listener);
        B33.setOnClickListener(listener);

        B1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Texto = ET1.getText().toString();
                Toast.makeText(getApplicationContext(),"Presionaste el boton, Texto Ingresado por el usuario"+Texto,Toast.LENGTH_SHORT).show();

            }
        });
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    void InicializarTags () { // Asignar un "Nombre" a un objeto
        B11.setTag("11");
        B12.setTag("12");
        B13.setTag("13");

        B21.setTag("21");
        B22.setTag("22");
        B23.setTag("23");

        B31.setTag("31");
        B32.setTag("32");
        B33.setTag("33");
    }



    void InicializarVistas () {
        B1 = findViewById(R.id.Button1);
        ET1 = findViewById(R.id.EditText1);
        B11 = findViewById(R.id.B11);
        B12 = findViewById(R.id.B12);
        B13 = findViewById(R.id.B13);
        B21 = findViewById(R.id.B21);
        B22 = findViewById(R.id.B22);
        B23 = findViewById(R.id.B23);
        B31 = findViewById(R.id.B31);
        B32 = findViewById(R.id.B32);
        B33 = findViewById(R.id.B33);
    }
}
